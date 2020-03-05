package com.regiva.ivmiit_schedule.di.module

import com.bumptech.glide.annotation.GlideModule
import com.regiva.ivmiit_schedule.di.provider.ApiProvider
import com.regiva.ivmiit_schedule.di.provider.OkHttpClientProvider
import com.regiva.ivmiit_schedule.model.data.network.ApiService
import okhttp3.OkHttpClient
import toothpick.config.Module

class ServerModule : Module() {
    init {
        bind(OkHttpClient::class.java).toProvider(OkHttpClientProvider::class.java).providesSingletonInScope()
        bind(ApiService::class.java).toProvider(ApiProvider::class.java).providesSingletonInScope()
        bind(GlideModule::class.java).singletonInScope()
    }
}