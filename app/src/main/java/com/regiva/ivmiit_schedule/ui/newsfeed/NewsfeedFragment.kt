package com.regiva.ivmiit_schedule.ui.newsfeed

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.badoo.mvicore.android.AndroidBindings
import com.badoo.mvicore.binder.using
import com.badoo.mvicore.modelWatcher
import com.regiva.ivmiit_schedule.R
import com.regiva.ivmiit_schedule.entity.newsfeed.PostModel
import com.regiva.ivmiit_schedule.model.data.feature.PostsFeature
import com.regiva.ivmiit_schedule.model.system.FlowRouter
import com.regiva.ivmiit_schedule.ui.base.MviFragment
import com.regiva.ivmiit_schedule.ui.newsfeed.adapter.PostsAdapter
import com.regiva.ivmiit_schedule.util.ErrorHandler
import com.regiva.ivmiit_schedule.util.setLoadingState
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.fragment_newsfeed.*

class NewsfeedFragment : MviFragment<NewsfeedFragment.ViewModel, NewsfeedFragment.UiEvents>() {

    override val layoutRes: Int
        get() = R.layout.fragment_newsfeed

    private val flowRouter: FlowRouter by scope()
    private val feature: PostsFeature by scope()
    private val errorHandler: ErrorHandler by scope()
    private val adapter: PostsAdapter by lazy {
        PostsAdapter(listOf())
    }

    private var isLoadingMore: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpBindings()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        onNext(UiEvents.OnGetNewsFeed)
    }

    private fun setUpBindings() {
        object : AndroidBindings<NewsfeedFragment>(this) {
            override fun setup(view: NewsfeedFragment) {
                binder.bind(view to feature using { event ->
                    when (event) {
                        is UiEvents.OnGetNewsFeed -> PostsFeature.Wish.GetAllPosts
                    }
                })
                binder.bind(feature to view using { state ->
                    ViewModel(
                        state.isLoading,
                        state.posts
                    )
                })
                binder.bind(feature.news to Consumer { news ->
                    when (news) {
                        is PostsFeature.News.GetAllPostsFailure -> errorHandler.proceed(news.throwable) { view.showError(it) }
                    }
                })
            }
        }.setup(this)
    }

    private fun initRecycler() {
        rv_posts.layoutManager = LinearLayoutManager(activity)
        rv_posts.adapter = adapter
        rv_posts.setOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val totalItemCount = (recyclerView.layoutManager as LinearLayoutManager).itemCount
                val lastVisibleItem = (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                if (isLoadingMore != true && lastVisibleItem == totalItemCount - 1) {
                    onNext(UiEvents.OnGetNewsFeed)
                    isLoadingMore = true
                }
            }

        })
    }

    private fun showPosts(posts: List<PostModel>) {
        adapter.updateList(posts)
    }

    override fun accept(vm: ViewModel) {
        modelWatcher<ViewModel> {
            watch(ViewModel::isLoading) { pb_loading?.setLoadingState(it) }
            watch(ViewModel::posts) {
                it?.let { showPosts(it) }
                isLoadingMore = false
            }
        }.invoke(vm)
    }

    sealed class UiEvents {
        object OnGetNewsFeed : UiEvents()
    }

    class ViewModel(
        val isLoading: Boolean,
        val posts: List<PostModel>?
    )
}