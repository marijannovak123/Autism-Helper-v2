package com.marijannovak.autismhelper.data.service

import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.marijannovak.autismhelper.common.errors.AppError
import com.marijannovak.autismhelper.data.models.domain.User
import com.marijannovak.autismhelper.data.models.requests.LoginRequest
import com.marijannovak.autismhelper.data.network.API
import com.marijannovak.autismhelper.data.network.apiRequest
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

interface AuthService {

    suspend fun login(request: LoginRequest): FirebaseUser

    suspend fun generateFirebaseUser(data: Intent): FirebaseUser

    suspend fun checkUserDataExistsAndIsValid(uid: String): User?

    suspend fun getUserData(uid: String): User?
}

class AuthServiceImpl @Inject constructor(
    private val api: API,
    private val firebaseAuth: FirebaseAuth
) : AuthService {

    override suspend fun login(request: LoginRequest): FirebaseUser =
        suspendCancellableCoroutine{ continuation ->
            firebaseAuth.signInWithEmailAndPassword(request.email, request.password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful && firebaseAuth.currentUser != null) {
                        val currentUser = firebaseAuth.currentUser
                        continuation.resume(currentUser!!)
                    } else {
                        continuation.resumeWithException(task.exception ?: AppError.Unknown)
                    }
                }
        }

    override suspend fun generateFirebaseUser(data: Intent): FirebaseUser =
        suspendCoroutine { continuation ->
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                val credential = GoogleAuthProvider.getCredential(account?.idToken, null)
                firebaseAuth.signInWithCredential(credential)
                    .addOnCompleteListener {
                        if (it.isSuccessful && firebaseAuth.currentUser != null) {
                            continuation.resume(firebaseAuth.currentUser!!)
                        } else {
                            continuation.resumeWithException(task.exception ?: AppError.Unknown)
                        }
                    }
            } catch (e: Exception) {
                continuation.resumeWithException(e)
            }
        }

    override suspend fun checkUserDataExistsAndIsValid(uid: String): User? =
         getUserData(uid)?.let { user ->
            if (user.isValidData) user else null
        }

    override suspend fun getUserData(uid: String) = apiRequest { api.getUser(uid) }

}