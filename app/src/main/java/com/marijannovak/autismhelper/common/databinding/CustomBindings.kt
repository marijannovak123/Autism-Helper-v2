package com.marijannovak.autismhelper.common.databinding

import androidx.annotation.StringRes
import androidx.appcompat.widget.AppCompatButton
import androidx.databinding.BindingAdapter
import com.google.android.gms.common.SignInButton
import com.google.android.material.textfield.TextInputLayout

object CustomBindings {
    @JvmStatic
    @BindingAdapter("isEnabled")
    fun setButtonEnabled(button: AppCompatButton, enabled: Boolean?) {
        button.isEnabled = enabled ?: false
    }

    @JvmStatic
    @BindingAdapter("error")
    fun setError(til: TextInputLayout, @StringRes stringRes: Int?) {
        til.error = stringRes?.let { til.context.getString(stringRes) }
    }

    @JvmStatic
    @BindingAdapter("onClick")
    fun setGoogleSignInOnClick(button: SignInButton, listener: () -> Unit) {
        button.setOnClickListener { listener() }
    }

}