package com.tesonet.task.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tesonet.task.R
import com.tesonet.task.repository.AuthRepository
import com.tesonet.task.repository.PersistentRepository
import io.reactivex.disposables.CompositeDisposable
import retrofit2.HttpException
import java.net.UnknownHostException

class LoginViewModel(
    private val authRepository: AuthRepository,
    private val persistentRepository: PersistentRepository
) : ViewModel() {

    val liveData = MutableLiveData<LoginUiState>()
    private val bag = CompositeDisposable()

    fun login(username: String, password: String) {

        if (username.isNotEmpty() && password.isNotEmpty()) {
            liveData.postValue(LoginUiState.ShowSpinner)
            getTokenFromApi(username, password)
        } else {
            liveData.postValue(LoginUiState.Error(R.string.no_username_password_error))
        }
    }

    private fun getTokenFromApi(username: String, password: String) {
        val disposable = authRepository.getToken(username, password)
            .subscribe(
                {
                    if (!it.token.isNullOrEmpty()) {
                        persistentRepository.saveTokenToPersistent(it.token)
                        liveData.postValue(LoginUiState.Success(it))
                    }
                },
                {
                    when (it) {
                        is UnknownHostException -> liveData.postValue(LoginUiState.Error(R.string.no_internet_connection_error))
                        is HttpException -> liveData.postValue(LoginUiState.Error(R.string.auth_error))
                        else -> liveData.postValue(LoginUiState.Error(R.string.unable_login_error))
                    }
                }
            )
        bag.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        bag.clear()
    }
}