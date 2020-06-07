package com.tesonet.task.di

import com.tesonet.task.repository.AuthRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { AuthRepository() }
}