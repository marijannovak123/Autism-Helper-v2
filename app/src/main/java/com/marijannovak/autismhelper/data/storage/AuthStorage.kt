package com.marijannovak.autismhelper.data.storage

import com.marijannovak.autismhelper.data.database.dao.ChildDao
import com.marijannovak.autismhelper.data.database.dao.ChildScoreDao
import com.marijannovak.autismhelper.data.database.dao.UserDao
import com.marijannovak.autismhelper.data.models.domain.User

class AuthStorage (
    private val userDao: UserDao,
    private val childDao: ChildDao,
    private val childScoreDao: ChildScoreDao
) {

   suspend fun isLoggedIn(): Boolean = userDao.getUser() != null

    suspend fun saveUser(user: User) {
        userDao.insert(user.toDatabase())
        //TODO: Handle persisting child data
    }

    suspend fun getUserUid() = userDao.getUser()?.id
}