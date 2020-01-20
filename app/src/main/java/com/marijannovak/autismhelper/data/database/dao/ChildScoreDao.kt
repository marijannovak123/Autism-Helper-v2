package com.marijannovak.autismhelper.data.database.dao

import androidx.room.Dao
import com.marijannovak.autismhelper.common.base.BaseDao
import com.marijannovak.autismhelper.data.models.persistence.DBChildScore

@Dao
interface ChildScoreDao: BaseDao<DBChildScore> {
}