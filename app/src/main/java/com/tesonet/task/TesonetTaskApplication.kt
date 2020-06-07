package com.tesonet.task

import android.app.Application
import com.tesonet.task.di.networkModule
import com.tesonet.task.di.repositoryModule
import com.tesonet.task.di.sharedPreferencesModule
import com.tesonet.task.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TesonetTaskApplicaiton : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@TesonetTaskApplicaiton)
            modules(viewModelModule, repositoryModule, sharedPreferencesModule, networkModule)
        }
    }
}