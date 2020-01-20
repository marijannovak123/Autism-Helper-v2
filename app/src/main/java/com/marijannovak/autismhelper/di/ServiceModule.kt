package com.marijannovak.autismhelper.di

import com.marijannovak.autismhelper.data.service.AuthService
import com.marijannovak.autismhelper.data.service.DataService
import org.koin.dsl.module

val serviceModule = module {
    single { AuthService(get(), get()) }
    single { DataService(get()) }
}