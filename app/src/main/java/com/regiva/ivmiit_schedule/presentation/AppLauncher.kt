package com.regiva.ivmiit_schedule.presentation

import android.util.Log
import com.regiva.ivmiit_schedule.Screens
import com.regiva.ivmiit_schedule.di.DI
import com.regiva.ivmiit_schedule.di.module.ServerModule
import com.regiva.ivmiit_schedule.model.interactors.AuthInteractor
import ru.terrakok.cicerone.Router
import toothpick.Toothpick
import javax.inject.Inject

class AppLauncher @Inject constructor(
    private val router: Router,
    private val authInteractor: AuthInteractor
) {

    fun initModules() {
        if (!Toothpick.isScopeOpen(DI.SERVER_SCOPE)) {
            Toothpick.openScopes(DI.APP_SCOPE, DI.SERVER_SCOPE)
                .installModules(ServerModule())
        }
    }

    fun coldStart() {
        //todo
        /*if (authInteractor.isLoggedIn())
            router.newRootScreen(Screens.Main())
        else
            router.newRootScreen(Screens.AuthFlow)*/
        Log.d("rere", "coldStart")
        router.newRootScreen(Screens.AuthFlow)
    }
}