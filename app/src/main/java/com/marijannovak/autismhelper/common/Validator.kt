package com.marijannovak.autismhelper.common

import android.util.Patterns
import com.marijannovak.autismhelper.R

sealed class InputType {
    object Unvalidated: InputType()
    object Required: InputType()
    object Email: InputType()
    object Password: InputType()
}

object Validator {

    fun resolveError(inputText: String?, inputType: InputType): Int? {
        if (inputType is InputType.Unvalidated) {
            return null
        } else {
            if (inputText.isNullOrBlank()) {
                return R.string.validationError_required
            }
            return when (inputType) {
                InputType.Email -> validateEmail(
                    inputText
                )
                InputType.Password -> if (inputText.length < 4) R.string.validationError_passwordTooShort else null
                else -> null
            }
        }

    }

    private fun validateEmail(email: String): Int? {
        return if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) null else R.string.validationError_malformedEmail
    }
}