package com.tesonet.task.ui.servers

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tesonet.task.repository.PersistentRepository
import com.tesonet.task.repository.ServersRepository
import io.reactivex.disposables.CompositeDisposable

class ServersViewModel(
    private val serversRepository: ServersRepository,
    private val persistentRepository: PersistentRepository
) : ViewModel() {

    val liveData = MutableLiveData<ServersUiState>()
    private val bag = CompositeDisposable()

    init {
            getServersFromApi(persistentRepository.getTokenFromPersistent() ?: "")
    }

    private fun getServersFromApi(token: String) {
        val disposable = serversRepository.getServersFromApi(token)
            .subscribe(
                { liveData.postValue(ServersUiState.Success(it)) },
                { liveData.postValue(ServersUiState.ErrorMsg(it.message.toString())) }
            )
        bag.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        bag.clear()
    }
}