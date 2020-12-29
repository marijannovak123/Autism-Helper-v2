package com.marijannovak.autismhelper.data.storage

import com.marijannovak.autismhelper.data.database.dao.ChildDao
import com.marijannovak.autismhelper.data.database.dao.ChildScoreDao
import com.marijannovak.autismhelper.data.database.dao.UserDao
import com.marijannovak.autismhelper.data.models.domain.User
import javax.inject.Inject
import javax.inject.Singleton

interface AuthStorage {

    suspend fun isLoggedIn(): Boolean

    suspend fun saveUser(user: User)

    suspend fun getUserUid(): String?
}

class AuthStorageImpl @Inject constructor(
    private val userDao: UserDao,
    private val childDao: ChildDao,
    private val childScoreDao: ChildScoreDao
) : AuthStorage {

   override suspend fun isLoggedIn(): Boolean = userDao.getUser() != null

    override suspend fun saveUser(user: User) {
        userDao.insert(user.toDatabase())
        //TODO: Handle persisting child data
    }

    override suspend fun getUserUid() = userDao.getUser()?.id
}