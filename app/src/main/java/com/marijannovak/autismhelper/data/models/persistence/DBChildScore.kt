package com.marijannovak.autismhelper.data.models.persistence

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.marijannovak.autismhelper.constants.DBConstants

@Entity(tableName = DBConstants.TABLE_CHILD_SCORES)
data class DBChildScore(
    @PrimaryKey
    val id: Int,
    val childId: String,
    val parentId: String,
    val timestamp: Long,
    val duration: Long,
    val mistakes: Int
)