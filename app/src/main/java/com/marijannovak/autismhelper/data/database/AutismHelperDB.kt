package com.marijannovak.autismhelper.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.marijannovak.autismhelper.data.models.persistence.DBMovie

@Database(entities = [DBMovie::class], version = 1, exportSchema = false)
abstract class AutismHelperDB: RoomDatabase() {
}