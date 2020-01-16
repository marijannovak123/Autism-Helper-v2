package com.marijannovak.autismhelper.data.models.requests

data class SignUpRequest(
    val email: String,
    val username: String,
    val password: String
)