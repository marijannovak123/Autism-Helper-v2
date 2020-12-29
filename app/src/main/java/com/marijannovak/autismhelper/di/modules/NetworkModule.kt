package com.marijannovak.autismhelper.di.modules

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.marijannovak.autismhelper.R
import com.marijannovak.autismhelper.constants.ApiConstants.BASE_URL
import com.marijannovak.autismhelper.data.network.API
import com.marijannovak.autismhelper.data.network.CustomConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideClient(@ApplicationContext context: Context): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(
                HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
            )

            addInterceptor { chain ->
                val original = chain.request()
                val httpUrl = original.url
                val newHttpUrl = httpUrl.newBuilder()
                    .addQueryParameter("auth", context.getString(R.string.FIREBASE_AUTH_KEY))
                    .build()
                val request = original.newBuilder().url(newHttpUrl).build()
                chain.proceed(request)
            }
        }.build()
    }

    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(CustomConverterFactory.Builder()
                .add(CustomConverterFactory.Xml::class.java, SimpleXmlConverterFactory.createNonStrict())
                .add(CustomConverterFactory.Json::class.java, MoshiConverterFactory.create())
                .build())
            .build()
    }

    @Provides
    fun provideApi(retrofit: Retrofit): API {
        return retrofit.create(API::class.java)
    }

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }
}
