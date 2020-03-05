package com.regiva.ivmiit_schedule.ui.student_schedule

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.badoo.mvicore.android.AndroidBindings
import com.badoo.mvicore.binder.using
import com.badoo.mvicore.modelWatcher
import com.regiva.ivmiit_schedule.R
import com.regiva.ivmiit_schedule.Screens
import com.regiva.ivmiit_schedule.entity.newsfeed.PostModel
import com.regiva.ivmiit_schedule.model.data.feature.PostsFeature
import com.regiva.ivmiit_schedule.model.system.FlowRouter
import com.regiva.ivmiit_schedule.ui.base.MviFragment
import com.regiva.ivmiit_schedule.ui.newsfeed.adapter.PostsAdapter
import com.regiva.ivmiit_schedule.util.ErrorHandler
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.fragment_choose_group.*

class ChooseGroupFragment : MviFragment<ChooseGroupFragment.ViewModel, ChooseGroupFragment.UiEvents>() {

    override val layoutRes: Int
        get() = R.layout.fragment_choose_group

    private val flowRouter: FlowRouter by scope()
    //todo
    private val feature: PostsFeature by scope()
    private val errorHandler: ErrorHandler by scope()
    private val adapter: PostsAdapter by lazy {
        PostsAdapter(listOf())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpBindings()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSpinner()
        setOnClickListeners()
//        onNext(UiEvents.OnGetNewsFeed)
    }

    private fun setUpBindings() {
        object : AndroidBindings<ChooseGroupFragment>(this) {
            override fun setup(view: ChooseGroupFragment) {
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

    private fun setOnClickListeners() {
        btn_show_schedule.setOnClickListener {
            flowRouter.navigateTo(
                Screens.GroupSchedule(
                    sp_course.selectedItem as String,
                    sp_group.selectedItem as String
                )
            )
        }
    }

    private fun initSpinner() {
        //todo
        val aa = ArrayAdapter.createFromResource(this.requireContext(), R.array.courses, android.R.layout.simple_spinner_item)
        // Set layout to use when the list of choices appear
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Set Adapter to Spinner
        sp_course.adapter = aa
        sp_course.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                initGroupsSpinner(R.array.first_course)
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when(position) {
                    0 -> initGroupsSpinner(R.array.first_course)
                    1 -> initGroupsSpinner(R.array.second_course)
                    2 -> initGroupsSpinner(R.array.third_course)
                    3 -> initGroupsSpinner(R.array.fourth_course)
                    4 -> initGroupsSpinner(R.array.fifth_course)
                }
            }

        }
    }

    private fun initGroupsSpinner(id: Int) {
        val aa = ArrayAdapter.createFromResource(this.requireContext(), id, android.R.layout.simple_spinner_item)
        // Set layout to use when the list of choices appear
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Set Adapter to Spinner
        sp_group.adapter = aa
        sp_group.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

            }

        }
    }

    private fun showPosts(posts: List<PostModel>) {
        adapter.updateList(posts)
    }

    override fun accept(vm: ViewModel) {
        modelWatcher<ViewModel> {
//            watch(ViewModel::isLoading) { pb_loading?.setLoadingState(it) }
            watch(ViewModel::posts) { it?.let { showPosts(it) } }
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