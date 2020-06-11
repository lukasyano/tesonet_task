package com.tesonet.task.di

import android.content.Context
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

const val TOKEN_VALUE_KEY = "TOKEN_VALUE"
const val TOKEN_DEFAULT_VALUE = ""

val sharedPreferencesModule = module {
    single {
        androidApplication().getSharedPreferences("shared_preferences", Context.MODE_PRIVATE)
    }
}