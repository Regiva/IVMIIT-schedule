package com.regiva.ivmiit_schedule.model.repositories

import com.regiva.ivmiit_schedule.model.data.network.ApiService
import com.regiva.ivmiit_schedule.model.data.storage.Prefs
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostsRepository @Inject constructor(
    private val apiService: ApiService,
    private val prefs: Prefs
) {

    fun getNewsfeed(start_from: String?) =
        apiService.getNewsfeed(
            token = prefs.token ?: "",
            start_from = start_from
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

}