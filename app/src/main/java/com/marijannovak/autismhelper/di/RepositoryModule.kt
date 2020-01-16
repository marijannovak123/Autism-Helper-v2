package com.marijannovak.autismhelper.di

import com.marijannovak.autismhelper.data.repository.AuthRepository
import com.marijannovak.autismhelper.data.repository.ResourceRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    single { AuthRepository(get(), get()) }
    single { ResourceRepository(androidContext()) }
}