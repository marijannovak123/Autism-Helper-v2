package com.marijannovak.autismhelper.ui.screens.auth


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.marijannovak.autismhelper.R
import com.marijannovak.autismhelper.common.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class CreateChildrenProfilesFragment : BaseFragment<AuthViewModel>() {

    override val viewModel: AuthViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_children_profiles, container, false)
    }

}
