package com.marijannovak.autismhelper.data.storage

import com.marijannovak.autismhelper.data.PrefsHelper
import com.marijannovak.autismhelper.data.database.dao.UserDao
import com.marijannovak.autismhelper.data.models.domain.User

class AuthStorage (
    private val prefs: PrefsHelper,
    private val userDao: UserDao
) {

   suspend fun isLoggedIn(): Boolean = userDao.getUser() != null

    suspend fun saveUser(user: User) {
        userDao.insert(user.toDatabase())
    }
}