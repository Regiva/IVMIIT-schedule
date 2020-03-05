package com.regiva.ivmiit_schedule

import com.regiva.ivmiit_schedule.ui.auth.AuthFlowFragment
import com.regiva.ivmiit_schedule.ui.auth.AuthorizeFragment
import com.regiva.ivmiit_schedule.ui.main.MainFragment
import com.regiva.ivmiit_schedule.ui.newsfeed.NewsfeedFlowFragment
import com.regiva.ivmiit_schedule.ui.newsfeed.NewsfeedFragment
import com.regiva.ivmiit_schedule.ui.student_schedule.ChooseGroupFragment
import com.regiva.ivmiit_schedule.ui.student_schedule.group_schedule.GroupScheduleFragment
import com.regiva.ivmiit_schedule.ui.student_schedule.StudentScheduleFlowFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {

    //flows
    object AuthFlow : SupportAppScreen() {
        override fun getFragment() = AuthFlowFragment()
    }

    //tab flows
    object NewsfeedFlow : SupportAppScreen() {
        override fun getFragment() = NewsfeedFlowFragment()
    }

    object StudentScheduleFlow : SupportAppScreen() {
        override fun getFragment() = StudentScheduleFlowFragment()
    }

    //screens
    object Authorize : SupportAppScreen() {
        override fun getFragment() = AuthorizeFragment()
    }

    data class Main(val currentTab: Int? = null) : SupportAppScreen() {
        override fun getFragment() = MainFragment.create(currentTab)
    }

    object Newsfeed : SupportAppScreen() {
        override fun getFragment() = NewsfeedFragment()
    }

    object ChooseGroup : SupportAppScreen() {
        override fun getFragment() = ChooseGroupFragment()
    }

    data class GroupSchedule(val course: String, val group: String) : SupportAppScreen() {
        override fun getFragment() = GroupScheduleFragment.create(course, group)
    }
}