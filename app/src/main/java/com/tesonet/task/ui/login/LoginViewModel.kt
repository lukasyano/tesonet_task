package com.tesonet.task.ui.login

import androidx.lifecycle.ViewModel
import com.tesonet.task.repository.AuthRepository
import com.tesonet.task.repository.entity.TokenEntity
import io.reactivex.Single

class LoginViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    fun login(username: String, password: String) {
        getToken(username, password)
    }

    private fun getToken(username: String, password: String): Single<TokenEntity>? {
        return authRepository.getToken(username, password)
    }
}