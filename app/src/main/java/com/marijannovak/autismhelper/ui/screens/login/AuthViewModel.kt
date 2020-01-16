package com.marijannovak.autismhelper.ui.screens.login

import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.marijannovak.autismhelper.R
import com.marijannovak.autismhelper.common.base.BaseViewModel
import com.marijannovak.autismhelper.common.events.EventLiveData
import com.marijannovak.autismhelper.common.events.UIEvent
import com.marijannovak.autismhelper.data.repository.AuthRepository
import com.marijannovak.autismhelper.ui.forms.LoginForm
import kotlinx.coroutines.delay

class AuthViewModel (
    private val repository: AuthRepository
): BaseViewModel() {

    val loginForm = LoginForm()

    val googleLoginEvent = EventLiveData<Intent>()

    fun login() {
        if (loginForm.validate()) {
            suspendCall {
                notifyEvent(UIEvent.ShowLoading)
                repository.login(loginForm.request)
                navigate(R.id.action_loginFragment_to_homeFragment)
            }
        }
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
            repository.getUserDataFromGoogleIntent(data)
            println("kita")
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