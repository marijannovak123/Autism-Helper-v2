package com.marijannovak.autismhelper.ui.screens.auth

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.marijannovak.autismhelper.R
import com.marijannovak.autismhelper.common.base.BaseFragment
import com.marijannovak.autismhelper.common.events.EventObserver
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment: BaseFragment<Nothing, Nothing>() {

    companion object {
        const val REQUEST_CODE_GOOGLE_SIGN_IN = 9001
    }

    override val viewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(container?.context).inflate(R.layout.fragment_login, container, false)
    }

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
