package com.marijannovak.autismhelper.di

import com.marijannovak.autismhelper.ui.screens.auth.AuthViewModel
import com.marijannovak.autismhelper.ui.screens.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { AuthViewModel(get()) }
    viewModel { HomeViewModel() }
}