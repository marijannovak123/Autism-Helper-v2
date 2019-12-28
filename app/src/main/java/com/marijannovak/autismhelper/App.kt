package com.marijannovak.autismhelper

import android.app.Application
import com.marijannovak.autismhelper.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    val diModules get() = listOf(
        commonModule,
        networkModule,
        dbModule,
        viewModelModule,
        repositoryModule,
        serviceModule,
        storageModule
    )

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(diModules)
        }
    }

}