package com.regiva.ivmiit_schedule.model.repositories

import com.regiva.ivmiit_schedule.model.data.storage.Prefs
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val prefs: Prefs
) {

    fun storeToken(token: String) {
        if (token != prefs.token) prefs.token = token
    }

}