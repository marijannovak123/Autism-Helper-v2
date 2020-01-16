package com.marijannovak.autismhelper.di

import com.marijannovak.autismhelper.data.storage.AuthStorage
import org.koin.dsl.module

val storageModule = module {
    single { AuthStorage(get(), get()) }
}