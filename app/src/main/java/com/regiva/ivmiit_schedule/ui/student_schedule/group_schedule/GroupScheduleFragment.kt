package com.regiva.ivmiit_schedule.ui.student_schedule.group_schedule

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.badoo.mvicore.modelWatcher
import com.regiva.ivmiit_schedule.R
import com.regiva.ivmiit_schedule.entity.LessonModel
import com.regiva.ivmiit_schedule.model.system.FlowRouter
import com.regiva.ivmiit_schedule.ui.base.MviFragment
import com.regiva.ivmiit_schedule.ui.student_schedule.group_schedule.adapter.LessonsAdapter
import com.regiva.ivmiit_schedule.util.ErrorHandler
import com.regiva.ivmiit_schedule.util.argument
import kotlinx.android.synthetic.main.fragment_schedule.*
import org.apache.poi.ss.usermodel.WorkbookFactory

class GroupScheduleFragment : MviFragment<GroupScheduleFragment.ViewModel, GroupScheduleFragment.UiEvents>() {

    companion object {
        fun create(
            course: String,
            group: String
        ) =
            GroupScheduleFragment().apply {
                arguments = bundleOf(
                    EXTRA_COURSE to course,
                    EXTRA_GROUP to group
                )
            }

        private const val EXTRA_COURSE = "course"
        private const val EXTRA_GROUP = "group"
    }

    override val layoutRes: Int
        get() = R.layout.fragment_schedule

    private val flowRouter: FlowRouter by scope()
//    private val feature: PostsFeature by scope()
    private val errorHandler: ErrorHandler by scope()
    private val adapter: LessonsAdapter by lazy {
        LessonsAdapter(listOf())
    }
    private val course by argument(EXTRA_COURSE, "")
    private val group by argument(EXTRA_GROUP, "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpBindings()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListeners()
        initRecycler()
        readSchedule()
//        onNext(UiEvents.OnGetNewsFeed)
    }

    private fun setUpBindings() {
        /*object : AndroidBindings<ChooseGroupFragment>(this) {
            override fun setup(view: ChooseGroupFragment) {
                binder.bind(view to feature using { event ->
                    when (event) {
                        is UiEvents.OnGetNewsFeed -> PostsFeature.Wish.GetAllPosts
                    }
                })
                binder.bind(feature to view using { state ->
                    ViewModel(
                        state.isLoading,
                        state.lessons
                    )
                })
                binder.bind(feature.news to Consumer { news ->
                    when (news) {
                        is PostsFeature.News.GetAllPostsFailure -> errorHandler.proceed(news.throwable) { view.showError(it) }
                    }
                })
            }
        }.setup(this)*/
    }

    private fun setOnClickListeners() {
//        btn_show_schedule.setOnClickListener {
//            readSchedule()
//        }
    }

    private fun initRecycler() {
        rv_lessons.layoutManager = LinearLayoutManager(context)
        rv_lessons.adapter = adapter
    }

    private fun readSchedule() {
        val inputStream = resources.openRawResource(R.raw.schedule_first_new)
        var xlWb = WorkbookFactory.create(inputStream)
        val xlWs = xlWb.getSheetAt(1)

        val list = arrayListOf<LessonModel>()
        for (row in xlWs.rowIterator()) {
            if (row.getCell(1).toString().isNullOrBlank()) continue
            if (row.getCell(16).toString().trim().split("; ").contains(group)) {
                list.add(
                    LessonModel(
                        id = row.getCell(1).toString(),
                        title = row.getCell(4).toString(),
                        teacher = row.getCell(9).toString(),
                        cabinet = row.getCell(15).toString().substringBeforeLast(".", "-"),
                        type = row.getCell(7).toString(),
                        serial = row.getCell(13).toString()
                    )
                )
            }
        }
        showLessons(list)
    }

    private fun showLessons(list: List<LessonModel>) {
        adapter.updateList(list)
    }

    override fun accept(vm: ViewModel) {
        modelWatcher<ViewModel> {
            watch(ViewModel::lessons) { it?.let { showLessons(it) } }
        }.invoke(vm)
    }

    sealed class UiEvents {
        object OnGetNewsFeed : UiEvents()
        object OnLoveJulya : UiEvents()
    }

    class ViewModel(
        val isLoading: Boolean,
        val lessons: List<LessonModel>?
    )
}