package com.tesonet.task.di

import android.content.Context
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val sharedPreferencesModule = module {
    single {
        androidApplication().getSharedPreferences("shared_preferences", Context.MODE_PRIVATE)
    }
}