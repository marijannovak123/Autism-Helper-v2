package com.marijannovak.autismhelper.common.base

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

/**
 * Handle all the generic inserts, updates and deletes
 */
interface BaseDao<M> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMultiple(models: List<M>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(model: M)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateMultiple(models: List<M>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(model: M)

    @Delete
    suspend fun deleteMultiple(models: List<M>)

    @Delete
    suspend fun delete(model: M)

}