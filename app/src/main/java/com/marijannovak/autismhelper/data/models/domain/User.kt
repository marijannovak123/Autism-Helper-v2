package com.marijannovak.autismhelper.data.models.domain

import com.squareup.moshi.Json

data class User(
    val id: String,
    val username: String?,
    val email: String?,
    val parentPassword: String?,
    val profilePicPath: String?,
    val children: Map<String, Child>?,
    @field:Json(name = "child_scores")
    val childScores: Map<String, ChildScore>?
)