package com.marijannovak.autismhelper.data.models.persistence

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.marijannovak.autismhelper.constants.DBConstants

@Entity(tableName = DBConstants.TABLE_CHILDREN)
data class DBChild(
    @PrimaryKey
    val id: String,
    val parentId: String,
    val name: String,
    val gender: String,
    val dateOfBirth: Long
)