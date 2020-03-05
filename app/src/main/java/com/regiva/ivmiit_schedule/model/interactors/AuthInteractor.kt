package com.regiva.ivmiit_schedule.model.interactors

import com.regiva.ivmiit_schedule.model.repositories.AuthRepository
import com.regiva.ivmiit_schedule.model.repositories.TokenRepository
import javax.inject.Inject

class AuthInteractor @Inject constructor(
    private val authRepository: AuthRepository,
    private val tokenRepository: TokenRepository
) {

    fun isLoggedIn() = tokenRepository.isLoggedIn()

    fun storeToken(token: String) = authRepository.storeToken(token)

}