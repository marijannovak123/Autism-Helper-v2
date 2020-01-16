package com.marijannovak.autismhelper.ui.forms

import com.marijannovak.autismhelper.common.InputType
import com.marijannovak.autismhelper.common.databinding.InputField
import com.marijannovak.autismhelper.common.databinding.UIForm
import com.marijannovak.autismhelper.data.models.requests.LoginRequest

class LoginForm: UIForm<LoginRequest>() {

    val email = InputField(InputType.Email)
    val password = InputField(InputType.Password)

    override val validatableFields: List<InputField>
        get() = listOf(email, password)

    override val request: LoginRequest
        get() = LoginRequest(email.safeValue, password.safeValue)

}