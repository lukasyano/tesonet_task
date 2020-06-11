package com.tesonet.task.ui.login

import androidx.annotation.StringRes
import com.tesonet.task.repository.entity.TokenEntity

sealed class LoginUiState {

    data class Success(val tokenEntity: TokenEntity) : LoginUiState()

    data class Error(@StringRes val errorMsg : Int) : LoginUiState()

    object ShowSpinner : LoginUiState()
}