package com.marijannovak.autismhelper.di.modules

import com.marijannovak.autismhelper.data.Prefs
import com.marijannovak.autismhelper.data.PrefsImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {

    @Binds
    fun bindPrefs(prefs: PrefsImpl): Prefs
}