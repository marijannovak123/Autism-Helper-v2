package com.marijannovak.autismhelper.data.models.persistence

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.marijannovak.autismhelper.constants.DBConstants

@Entity(tableName = DBConstants.TABLE_ANSWERS)
data class DBQuizAnswer(
    @PrimaryKey
    val id: Int,
    val text: String,
    val isCorrect: Boolean,
    val questionId: Int
)