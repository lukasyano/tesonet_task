package com.tesonet.task.di

import com.tesonet.task.data.Constants
import com.tesonet.task.network.ApiService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { retrofit.create(ApiService::class.java) }
}

private val httpClient = OkHttpClient.Builder()
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(Constants.TESONET_PLAYGROUND_BASE_URL)
    .client(httpClient)
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .build()
