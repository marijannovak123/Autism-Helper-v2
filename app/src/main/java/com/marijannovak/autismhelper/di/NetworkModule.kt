package com.marijannovak.autismhelper.di

import com.marijannovak.autismhelper.R
import com.marijannovak.autismhelper.common.Constants.BASE_URL
import com.marijannovak.autismhelper.data.network.API
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        val client = OkHttpClient.Builder()
            .connectTimeout(3, TimeUnit.SECONDS)
            .readTimeout(3, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)

        client.addInterceptor(
            HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        )

        client.addInterceptor { chain ->
            val original = chain.request()
            val httpUrl = original.url()
            val newHttpUrl = httpUrl.newBuilder()
                .addQueryParameter("auth", androidContext().getString(R.string.FIREBASE_AUTH_KEY))
                .build()
            val request = original.newBuilder().url(newHttpUrl).build()
            chain.proceed(request)
        }

        client.build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>().create(API::class.java)
    }

}