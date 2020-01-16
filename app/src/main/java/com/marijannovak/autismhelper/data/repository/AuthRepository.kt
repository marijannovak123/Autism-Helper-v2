package com.marijannovak.autismhelper.data.repository

import android.content.Intent
import com.marijannovak.autismhelper.data.models.requests.LoginRequest
import com.marijannovak.autismhelper.data.service.AuthService
import com.marijannovak.autismhelper.data.storage.AuthStorage

class AuthRepository (
    private val authService: AuthService,
    private val authStorage: AuthStorage
) {

    suspend fun isLoggedIn(): Boolean = authStorage.isLoggedIn()

    suspend fun login(request: LoginRequest) {

    }

    /**
     * The login process with the generated FirebaseUser
     * returns boolean if it needs to run the profile creating (add children etc.)
     */
    suspend fun loginWithUserDataFromGoogleIntent(data: Intent): Boolean {
        val firebaseUser = authService.generateFirebaseUser(data)
        val user = authService.checkUserDataExistsAndIsValid(firebaseUser.uid)
        return if (user != null) {
            authStorage.saveUser(user)
            false
        } else {
            true
        }
    }

}