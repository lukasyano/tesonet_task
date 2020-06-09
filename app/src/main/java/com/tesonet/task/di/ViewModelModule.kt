package com.tesonet.task.di

import com.tesonet.task.ui.login.LoginViewModel
import com.tesonet.task.ui.servers.ServersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { ServersViewModel(get()) }
}