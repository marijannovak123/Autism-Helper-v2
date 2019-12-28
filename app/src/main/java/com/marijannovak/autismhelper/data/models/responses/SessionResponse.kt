package com.marijannovak.autismhelper.data.models.responses

import com.squareup.moshi.Json

data class SessionResponse (
    @field:Json(name = "session_id")
    val sessionId: String
)