package com.marijannovak.autismhelper.data.models.persistence

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.marijannovak.autismhelper.constants.DBConstants

@Entity(tableName = DBConstants.TABLE_CATEGORIES)
data class DBQuizCategory(
    @PrimaryKey
    val id: Int,
    val name: String
)