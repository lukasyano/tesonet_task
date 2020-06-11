package com.tesonet.task.ui.servers

import com.tesonet.task.repository.entity.ServersEntity

sealed class ServersUiState {

    data class Success(val servers: List<ServersEntity>) : ServersUiState()

    data class ErrorMsg(var errorText: String) : ServersUiState()

}
