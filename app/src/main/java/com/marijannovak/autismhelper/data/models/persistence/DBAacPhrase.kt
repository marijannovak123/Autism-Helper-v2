package com.marijannovak.autismhelper.data.models.persistence

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.marijannovak.autismhelper.constants.DBConstants

@Entity(tableName = DBConstants.TABLE_AAC)
data class DBAacPhrase(
    @PrimaryKey
    val id: Int,
    val name: String,
    val text: String,
    val iconPath: String,
    val categoryId: Int
)