package com.marijannovak.autismhelper.data.models.responses

import com.squareup.moshi.Json

data class TokenResponse (
    val success: Boolean,
    @field:Json(name = "expires_at")
    val expiresAt: String,
    @field:Json(name = "request_token")
    val requestToken: String
)