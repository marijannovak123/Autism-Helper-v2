package com.marijannovak.autismhelper.di

import com.google.firebase.auth.FirebaseAuth
import com.marijannovak.autismhelper.R
import com.marijannovak.autismhelper.constants.ApiConstants.BASE_URL
import com.marijannovak.autismhelper.data.network.API
import com.marijannovak.autismhelper.data.network.CustomConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

val networkModule = module {
    single {
        OkHttpClient.Builder().apply {
            addInterceptor(
                HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
            )

            addInterceptor { chain ->
                val original = chain.request()
                val httpUrl = original.url()
                val newHttpUrl = httpUrl.newBuilder()
                    .addQueryParameter("auth", androidContext().getString(R.string.FIREBASE_AUTH_KEY))
                    .build()
                val request = original.newBuilder().url(newHttpUrl).build()
                chain.proceed(request)
            }
        }.build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(BASE_URL)
            .addConverterFactory(CustomConverterFactory.Builder()
                .add(CustomConverterFactory.Xml::class.java, SimpleXmlConverterFactory.createNonStrict())
                .add(CustomConverterFactory.Json::class.java, MoshiConverterFactory.create())
                .build())
            .build()
    }

    single {
        get<Retrofit>().create(API::class.java)
    }

    single {
        FirebaseAuth.getInstance()
    }

}