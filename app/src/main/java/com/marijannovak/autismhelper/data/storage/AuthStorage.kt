package com.marijannovak.autismhelper.data.storage

import com.marijannovak.autismhelper.data.PrefsHelper
import com.marijannovak.autismhelper.data.database.UserDao

class AuthStorage (
    private val prefs: PrefsHelper,
    private val userDao: UserDao
) {

   suspend fun isLoggedIn(): Boolean = userDao.getUser() != null

}