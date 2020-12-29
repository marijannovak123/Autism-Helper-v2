package com.marijannovak.autismhelper.di.modules

import com.marijannovak.autismhelper.data.service.AuthService
import com.marijannovak.autismhelper.data.service.AuthServiceImpl
import com.marijannovak.autismhelper.data.service.DataService
import com.marijannovak.autismhelper.data.service.DataServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ServiceModule {

    @Binds
    fun bindAuthService(authServiceImpl: AuthServiceImpl): AuthService

    @Binds
    fun bindDataService(dataServiceImpl: DataServiceImpl): DataService
}
