package com.marijannovak.autismhelper.data.models.persistence

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DBMovie (
    @PrimaryKey
    val id: Int
)