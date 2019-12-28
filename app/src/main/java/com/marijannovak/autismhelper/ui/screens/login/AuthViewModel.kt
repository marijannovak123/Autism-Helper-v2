package com.marijannovak.autismhelper.ui.screens.login

import com.marijannovak.autismhelper.R
import com.marijannovak.autismhelper.common.BaseViewModel
import com.marijannovak.autismhelper.common.events.UIEvent
import com.marijannovak.autismhelper.data.repository.AuthRepository
import com.marijannovak.autismhelper.ui.forms.LoginForm

class AuthViewModel (private val repository: AuthRepository): BaseViewModel() {

    val loginForm = LoginForm()

    fun login() {
        if (loginForm.validate()) {
            suspendCall {
                notifyEvent(UIEvent.ShowLoading)
                repository.login(loginForm.request)
                navigate(R.id.action_loginFragment_to_homeFragment)
            }
        }
    }

}