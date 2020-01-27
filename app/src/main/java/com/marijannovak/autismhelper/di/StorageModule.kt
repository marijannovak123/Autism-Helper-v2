package com.marijannovak.autismhelper.di

import com.marijannovak.autismhelper.data.storage.AuthStorage
import com.marijannovak.autismhelper.data.storage.DataStorage
import org.koin.dsl.module

val storageModule = module {
    single { AuthStorage(get(), get(), get()) }
    single { DataStorage(get(), get(), get(), get(), get(), get()) }
}
