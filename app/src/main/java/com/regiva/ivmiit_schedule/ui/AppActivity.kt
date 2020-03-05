package com.regiva.ivmiit_schedule.ui

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.badoo.mvicore.android.AndroidBindings
import com.google.android.material.snackbar.Snackbar
import com.regiva.ivmiit_schedule.R
import com.regiva.ivmiit_schedule.di.DI
import com.regiva.ivmiit_schedule.model.system.AppConnectivityManager
import com.regiva.ivmiit_schedule.presentation.AppLauncher
import com.regiva.ivmiit_schedule.ui.base.BaseFragment
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.layout_container.*
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command
import toothpick.Toothpick
import javax.inject.Inject

class AppActivity : AppCompatActivity(), Consumer<Boolean> {

    @Inject
    lateinit var appLauncher: AppLauncher

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var appConnectivityManager: AppConnectivityManager

    private val currentFragment: BaseFragment?
        get() = supportFragmentManager.findFragmentById(R.id.container) as? BaseFragment

    private val navigator: Navigator =
        object : SupportAppNavigator(this, supportFragmentManager, R.id.container) {
            override fun setupFragmentTransaction(
                command: Command?,
                currentFragment: Fragment?,
                nextFragment: Fragment?,
                fragmentTransaction: FragmentTransaction
            ) {
                fragmentTransaction.setCustomAnimations(
                    R.anim.slide_in_right,
                    R.anim.slide_out_left,
                    R.anim.slide_in_left,
                    R.anim.slide_out_right
                )
                fragmentTransaction.setReorderingAllowed(true)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        Toothpick.inject(this, Toothpick.openScope(DI.APP_SCOPE))
        appLauncher.initModules()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_container)
        setUpBindings()
        sendBroadcasts()
        System.setProperty("org.apache.poi.javax.xml.stream.XMLInputFactory", "com.fasterxml.aalto.stax.InputFactoryImpl")
        System.setProperty("org.apache.poi.javax.xml.stream.XMLOutputFactory", "com.fasterxml.aalto.stax.OutputFactoryImpl")
        System.setProperty("org.apache.poi.javax.xml.stream.XMLEventFactory", "com.fasterxml.aalto.stax.EventFactoryImpl")
        if (savedInstanceState == null) {
            appLauncher.coldStart()
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    private fun setUpBindings() {
        object : AndroidBindings<AppActivity>(this) {
            override fun setup(view: AppActivity) {
                binder.bind(appConnectivityManager.source to view)
            }
        }.setup(this)
    }

    private fun sendBroadcasts() {
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION).apply {
            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        }
        registerReceiver(appConnectivityManager, filter)
    }

    override fun accept(isConnectedToNet: Boolean) {
        if (isConnectedToNet)
            showSuccess(getString(R.string.have_connection))
        else
            showError(getString(R.string.lost_connection))
    }

    override fun onBackPressed() {
        currentFragment?.onBackPressed() ?: super.onBackPressed()
    }

    fun showError(message: String) {
        Snackbar.make(container, message, Snackbar.LENGTH_SHORT)
            .setBackgroundTint(resources.getColor(R.color.errorRed))
            .setTextColor(resources.getColor(R.color.white))
            .apply {
                view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)?.let {
                    it.maxLines = 5
                }
            }
            .show()
    }

    fun showSuccess(message: String) {
        Snackbar.make(container, message, Snackbar.LENGTH_SHORT)
            .setBackgroundTint(resources.getColor(R.color.successGreen))
            .setTextColor(resources.getColor(R.color.white))
            .show()
    }

}