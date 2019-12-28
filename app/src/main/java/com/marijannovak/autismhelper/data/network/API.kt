package com.marijannovak.autismhelper.data.network

import com.marijannovak.autismhelper.data.models.requests.CreateSessionRequest
import com.marijannovak.autismhelper.data.models.requests.LoginRequest
import com.marijannovak.autismhelper.data.models.responses.SessionResponse
import com.marijannovak.autismhelper.data.models.responses.TokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface API {

    @GET("authentication/token/new")
    suspend fun generateRequestToken(): Response<TokenResponse>

    @POST("authentication/token/validate_with_login")
    suspend fun login(@Body request: LoginRequest): Response<TokenResponse>

    @POST("authentication/session/new")
    suspend fun createSession(@Body request: CreateSessionRequest): Response<SessionResponse>

}