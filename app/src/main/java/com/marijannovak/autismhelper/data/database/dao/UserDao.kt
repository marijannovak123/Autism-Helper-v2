package com.marijannovak.autismhelper.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.marijannovak.autismhelper.common.base.BaseDao
import com.marijannovak.autismhelper.constants.DBConstants.TABLE_USER
import com.marijannovak.autismhelper.data.models.persistence.DBUser

@Dao
interface UserDao: BaseDao<DBUser> {

    @Query("Select * from $TABLE_USER limit 1")
    suspend fun getUser(): DBUser?

}