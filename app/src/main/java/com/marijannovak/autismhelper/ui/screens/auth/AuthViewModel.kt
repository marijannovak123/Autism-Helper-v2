package com.marijannovak.autismhelper.ui.screens.auth

import android.content.Intent
import androidx.hilt.lifecycle.ViewModelInject
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.marijannovak.autismhelper.R
import com.marijannovak.autismhelper.common.base.BaseViewModel
import com.marijannovak.autismhelper.common.events.EventLiveData
import com.marijannovak.autismhelper.common.events.UIEvent
import com.marijannovak.autismhelper.data.repository.AuthRepository
import kotlinx.coroutines.delay

class AuthViewModel @ViewModelInject constructor(
    private val repository: AuthRepository
): BaseViewModel<Nothing, Nothing>() {

    val googleLoginEvent = EventLiveData<Intent>()

    fun login() {
//        if (loginForm.validate()) {
//            suspendCall {
//                notifyEvent(UIEvent.ShowLoading)
//                repository.login(loginForm.request)
//                navigate(R.id.action_loginFragment_to_homeFragment)
//            }
//        }
    }

    fun googleLogin() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(res.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        val googleSignInClient = GoogleSignIn.getClient(res.context, gso)
        googleLoginEvent.setEventValue(googleSignInClient.signInIntent)
    }

    fun onGoogleSignInResult(data: Intent) {
        suspendCall {
            notifyEvent(UIEvent.ShowLoading)
            val createProfile = repository.loginWithUserDataFromGoogleIntent(data)
            if (createProfile) {
                navigate(R.id.action_loginFragment_to_createChildrenProfilesFragment)
            } else {
                navigate(R.id.action_loginFragment_to_homeFragment)
            }
        }
    }

    fun decideStartingScreen() {
        suspendCall {
            delay(1000) // Show splash screen for at least a second
            if (repository.isLoggedIn()) {
                navigate(R.id.action_splashFragment_to_homeFragment)
            } else {
                navigate(R.id.action_splashFragment_to_loginFragment)
            }
        }
    }

}