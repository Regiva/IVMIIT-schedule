package com.regiva.ivmiit_schedule.ui.main

import android.os.Bundle
import androidx.core.os.bundleOf
import com.regiva.ivmiit_schedule.R
import com.regiva.ivmiit_schedule.Screens
import com.regiva.ivmiit_schedule.ui.base.BaseFragment
import com.regiva.ivmiit_schedule.ui.base.FlowFragment
import kotlinx.android.synthetic.main.fragment_main.*
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppScreen

class MainFragment : BaseFragment() {

    companion object {
        fun create(currentTab: Int? = null) =
            MainFragment().apply {
                arguments = bundleOf(
                    EXTRA_CURRENT_TAB to currentTab
                )
            }

        private const val EXTRA_CURRENT_TAB = "EXTRA_CURRENT_TAB"
    }

    private val router: Router by scope()

    private var currentTab: Int? = null
    private val newsfeedTab by lazy { Screens.NewsfeedFlow }
    private val studentScheduleTab by lazy { Screens.StudentScheduleFlow }
    private val teacherScheduleTab by lazy { Screens.NewsfeedFlow }
    private val calendarTab by lazy { Screens.NewsfeedFlow }
    private val uniInfoTab by lazy { Screens.NewsfeedFlow }

    override val layoutRes: Int
        get() = R.layout.fragment_main

    private val currentTabFragment: FlowFragment?
        get() = childFragmentManager.fragments.firstOrNull { !it.isHidden } as? FlowFragment

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initBottomNavigation()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments?.containsKey(EXTRA_CURRENT_TAB) == true) {
            currentTab = arguments!!.getInt(EXTRA_CURRENT_TAB)
        }
    }

    private fun initBottomNavigation() {
        bnv_main.itemIconTintList = null
        bnv_main.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.newsfeed -> selectTab(newsfeedTab)
                R.id.student -> selectTab(studentScheduleTab)
                else -> {
                    showError(getString(R.string.in_development))
                }
            }
            true
        }
        bnv_main.selectedItemId = currentTab ?: R.id.home
        selectTab(
            when (currentTabFragment?.tag) {
                newsfeedTab.screenKey -> newsfeedTab
                studentScheduleTab.screenKey -> studentScheduleTab
                else -> newsfeedTab
            }
        )
    }

    private fun selectTab(tab: SupportAppScreen) {
        val currentFragment = currentTabFragment
        val newFragment = childFragmentManager.findFragmentByTag(tab.screenKey)
        if (currentFragment != null && newFragment != null && currentFragment == newFragment) return
        childFragmentManager.beginTransaction().apply {
            if (newFragment == null) add(
                R.id.mainScreenContainer,
                createTabFragment(tab),
                tab.screenKey
            )
            currentFragment?.let {
                hide(it)
                it.userVisibleHint = false
            }
            newFragment?.let {
                show(it)
                it.userVisibleHint = true
            }
        }.commitNow()
    }

    private fun createTabFragment(tab: SupportAppScreen) = tab.fragment

    override fun onBackPressed() {
        currentTabFragment?.onBackPressed()
    }
}