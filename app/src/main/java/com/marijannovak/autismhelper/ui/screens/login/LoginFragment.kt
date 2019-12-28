package com.marijannovak.autismhelper.ui.screens.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marijannovak.autismhelper.common.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.databinding.DataBindingUtil
import com.marijannovak.autismhelper.R
import com.marijannovak.autismhelper.databinding.FragmentLoginBinding

class LoginFragment: BaseFragment<AuthViewModel>() {

    override val viewModel: AuthViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return DataBindingUtil.inflate<FragmentLoginBinding>(
            inflater, R.layout.fragment_login, container, false
        ).apply {
            lifecycleOwner = this@LoginFragment
            viewModel = this@LoginFragment.viewModel
        }.root
    }

}