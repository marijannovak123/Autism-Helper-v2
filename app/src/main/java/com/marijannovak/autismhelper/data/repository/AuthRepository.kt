package com.marijannovak.autismhelper.data.repository

import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.marijannovak.autismhelper.common.errors.AppError
import com.marijannovak.autismhelper.data.models.requests.LoginRequest
import com.marijannovak.autismhelper.data.service.AuthService
import com.marijannovak.autismhelper.data.storage.AuthStorage
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class AuthRepository (
    private val authService: AuthService,
    private val authStorage: AuthStorage
) {

    suspend fun isLoggedIn(): Boolean = authStorage.isLoggedIn()

    fun login(request: LoginRequest) {

    }

    suspend fun getUserDataFromGoogleIntent(data: Intent): FirebaseUser =
        suspendCoroutine { continuation ->
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val auth = FirebaseAuth.getInstance()
                val account = task.getResult(ApiException::class.java)
                val credential = GoogleAuthProvider.getCredential(account?.idToken, null)
                auth.signInWithCredential(credential)
                    .addOnCompleteListener {
                        if (it.isSuccessful && auth.currentUser != null) {
                            continuation.resume(auth.currentUser!!)
                        } else {
                            continuation.resumeWithException(AppError.NonExistentUser)
                        }
                    }
            } catch (e: ApiException) {
                continuation.resumeWithException(AppError.Unknown)
            }
        }

}