package com.regiva.ivmiit_schedule.di.module

import android.content.Context
import com.google.gson.Gson
import com.regiva.ivmiit_schedule.di.provider.GsonProvider
import com.regiva.ivmiit_schedule.model.data.storage.Prefs
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import toothpick.config.Module

class AppModule(
    context: Context
) : Module() {
    init {
        bind(Context::class.java).toInstance(context)
        bind(Gson::class.java).toProvider(GsonProvider::class.java).providesSingletonInScope()
        bind(Prefs::class.java).singletonInScope()
        // navigation
        val cicerone = Cicerone.create()
        bind(Router::class.java).toInstance(cicerone.router)
        bind(NavigatorHolder::class.java).toInstance(cicerone.navigatorHolder)
    }
}