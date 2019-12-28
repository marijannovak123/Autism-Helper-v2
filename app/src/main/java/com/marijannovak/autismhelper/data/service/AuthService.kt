package com.marijannovak.autismhelper.data.service

import com.marijannovak.autismhelper.data.models.requests.CreateSessionRequest
import com.marijannovak.autismhelper.data.models.requests.LoginRequest
import com.marijannovak.autismhelper.data.network.API
import com.marijannovak.autismhelper.common.apiRequest

class AuthService (private val api: API) {

    suspend fun generateRequestToken(): String {
        return apiRequest { api.generateRequestToken() }
            .requestToken
    }

    suspend fun login(request: LoginRequest): String {
        return apiRequest {
            api.login(
                request
            )
        }.requestToken
    }

    suspend fun createSession(requestToken: String): String {
        return apiRequest {
            api.createSession(
                CreateSessionRequest(requestToken)
            )
        }.sessionId
    }

}