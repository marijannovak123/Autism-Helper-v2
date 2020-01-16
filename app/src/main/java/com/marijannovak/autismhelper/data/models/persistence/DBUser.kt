package com.marijannovak.autismhelper.data.models.persistence

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.marijannovak.autismhelper.constants.DBConstants

@Entity(tableName = DBConstants.TABLE_USER)
data class DBUser(
    @PrimaryKey
    val id: String,
    val username: String?,
    val email: String?,
    val parentPassword: String?,
    val profilePicPath: String?
)