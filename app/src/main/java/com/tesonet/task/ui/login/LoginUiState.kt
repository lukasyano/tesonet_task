package com.tesonet.task.ui.login

import com.tesonet.task.repository.entity.TokenEntity

sealed class LoginUiState {

    data class Success(val tokenEntity: TokenEntity) : LoginUiState()

    data class Error(val error: String) : LoginUiState()
}