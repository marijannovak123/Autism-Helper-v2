package com.marijannovak.autismhelper.di.modules

import com.marijannovak.autismhelper.data.repository.AuthRepository
import com.marijannovak.autismhelper.data.repository.AuthRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindAuthRepository(authRepository: AuthRepositoryImpl): AuthRepository
}
