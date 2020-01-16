package com.marijannovak.autismhelper.data.models.requests

data class UserUpdateRequest(
    var username: String,
    var parentPassword: String,
    var profilePicPath: String
)