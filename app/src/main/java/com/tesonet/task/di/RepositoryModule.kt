package com.tesonet.task.di

import com.tesonet.task.repository.AuthRepository
import com.tesonet.task.repository.PersistentRepository
import com.tesonet.task.repository.ServersRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { AuthRepository(get()) }
    single { ServersRepository(get()) }
    single { PersistentRepository(get()) }
}