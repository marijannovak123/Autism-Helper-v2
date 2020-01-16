package com.marijannovak.autismhelper.data.models.domain

import com.marijannovak.autismhelper.data.models.persistence.DBUser
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
) {
    fun toDatabase() = DBUser(
        id, username, email, parentPassword, profilePicPath
    )

    val isValidData get() = (id.isNotEmpty() && username != null
            && username.isNotEmpty() && email != null
            && email.isNotEmpty())
}