package com.marijannovak.autismhelper.di

import com.marijannovak.autismhelper.common.Constants
import com.marijannovak.autismhelper.data.PrefsHelper
import com.tumblr.remember.Remember
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val commonModule = module {
    single {
        Remember.init(androidContext(), Constants.PREFS_NAME)
        PrefsHelper()
    }
}