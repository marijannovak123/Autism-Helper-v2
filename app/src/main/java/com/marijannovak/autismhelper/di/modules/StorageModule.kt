package com.marijannovak.autismhelper.di.modules

import com.marijannovak.autismhelper.data.storage.AuthStorage
import com.marijannovak.autismhelper.data.storage.AuthStorageImpl
import com.marijannovak.autismhelper.data.storage.DataStorage
import com.marijannovak.autismhelper.data.storage.DataStorageImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface StorageModule {

    @Binds
    fun bindAuthStorage(authStorageImpl: AuthStorageImpl): AuthStorage

    @Binds
    fun bindDataStorage(dataStorageImpl: DataStorageImpl): DataStorage
}
