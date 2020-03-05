package com.regiva.ivmiit_schedule.model.repositories

import com.regiva.ivmiit_schedule.model.data.storage.Prefs
import javax.inject.Inject

class TokenRepository @Inject constructor(
    private val prefs: Prefs
) {

    fun getToken() = prefs.token

    fun isLoggedIn() = prefs.token != null

    fun clearAccount() = prefs.removeSharedPreferences()

}