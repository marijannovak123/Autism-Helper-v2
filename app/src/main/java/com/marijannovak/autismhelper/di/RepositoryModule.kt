package com.marijannovak.autismhelper.di

import com.marijannovak.autismhelper.data.repository.AuthRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { AuthRepository(get(), get()) }
}