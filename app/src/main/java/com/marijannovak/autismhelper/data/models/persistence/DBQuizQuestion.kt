package com.marijannovak.autismhelper.data.models.persistence

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.marijannovak.autismhelper.constants.DBConstants

@Entity(tableName = DBConstants.TABLE_QUESTIONS)
data class DBQuizQuestion(
    @PrimaryKey
    val id: Int,
    val text: String,
    val categoryId: Int,
    val extraData: String?,
    val imgPath: String?
)