package com.marijannovak.autismhelper.di

import com.marijannovak.autismhelper.constants.PrefsConstants
import com.marijannovak.autismhelper.data.PrefsHelper
import com.tumblr.remember.Remember
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val commonModule = module {
    single {
        Remember.init(androidContext(), PrefsConstants.PREFS_NAME)
        PrefsHelper()
    }
}