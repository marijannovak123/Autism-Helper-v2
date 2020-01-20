package com.marijannovak.autismhelper.data.models.persistence

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.marijannovak.autismhelper.constants.DBConstants

@Entity(tableName = DBConstants.TABLE_SENTENCE)
data class DBSavedSentence(
    @PrimaryKey
    val id: Int
)