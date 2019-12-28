package com.marijannovak.autismhelper.data.models.requests

import com.squareup.moshi.Json

data class CreateSessionRequest (
    @field:Json(name = "request_token")
    val requestToken: String
)