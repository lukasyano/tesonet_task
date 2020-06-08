package com.tesonet.task.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tesonet.task.repository.AuthRepository
import io.reactivex.disposables.CompositeDisposable

class LoginViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    val liveData = MutableLiveData<LoginUiState>()
    private val bag = CompositeDisposable()

    fun login(username: String, password: String) {
        getTokenFromApi(username, password)
    }

    private fun getTokenFromApi(username: String, password: String) {
        val disposable = authRepository.getToken(username, password)
            .subscribe(
                {
                    if (!it.token.isNullOrEmpty()) {
                        authRepository.saveTokenToPersistent(it.token)
                        liveData.postValue(LoginUiState.Success(it))
                    } else {
                        if (!it.message.isNullOrEmpty()) {
                            liveData.postValue(LoginUiState.Success(it))
                        } else {
                            liveData.postValue(LoginUiState.Error("Unable to login"))
                        }
                    }
                },
                { liveData.postValue(LoginUiState.Error(it.message.toString())) }
            )
        bag.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        bag.clear()
    }


}