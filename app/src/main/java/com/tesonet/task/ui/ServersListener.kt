package com.tesonet.task.ui

import com.tesonet.task.repository.entity.ServersEntity

interface ServersListener {
    fun onServerClick(server : ServersEntity)
}
