package com.marijannovak.autismhelper.data.repository

import com.marijannovak.autismhelper.data.models.requests.LoginRequest
import com.marijannovak.autismhelper.data.service.AuthService
import com.marijannovak.autismhelper.data.storage.AuthStorage

class AuthRepository (
    private val authService: AuthService,
    private val authStorage: AuthStorage
) {
    suspend fun login(request: LoginRequest) {
        val requestToken = authService.generateRequestToken()
        val loginRequestToken = authService.login(request)
        val sessionId = authService.createSession(loginRequestToken)
        authStorage.saveSessionId(sessionId)
    }
}