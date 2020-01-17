package com.marijannovak.autismhelper.ui.screens.auth

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.marijannovak.autismhelper.R
import com.marijannovak.autismhelper.common.base.BaseFragment
import com.marijannovak.autismhelper.common.events.EventObserver
import com.marijannovak.autismhelper.constants.AppConstants.REQUEST_CODE_GOOGLE_SIGN_IN
import com.marijannovak.autismhelper.databinding.FragmentLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment: BaseFragment<AuthViewModel>() {

    override val viewModel: AuthViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = DataBindingUtil.inflate<FragmentLoginBinding>(
        inflater, R.layout.fragment_login, container, false
    ).apply {
        lifecycleOwner = this@LoginFragment
        viewModel = this@LoginFragment.viewModel
    }.root

    override fun observe() {
        super.observe()
        viewModel.googleLoginEvent.observe(viewLifecycleOwner, EventObserver {
            startActivityForResult(it, REQUEST_CODE_GOOGLE_SIGN_IN)
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            REQUEST_CODE_GOOGLE_SIGN_IN -> data?.let(viewModel::onGoogleSignInResult)
            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }

}
