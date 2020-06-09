package com.tesonet.task.repository.mapper

import com.tesonet.task.network.model.ServersResponse
import com.tesonet.task.network.model.TokenResponse
import com.tesonet.task.repository.entity.ServersEntity
import com.tesonet.task.repository.entity.TokenEntity

object Mapper {

    fun mapTokenResponse(result: TokenResponse): TokenEntity {
        return TokenEntity(
            message = result.message,
            token = result.token
        )
    }

    fun mapServersListResponse(result: List<ServersResponse>): List<ServersEntity> {
        return result.map {
            ServersEntity(
                name = it.name ?: "Unknown",
                distance = it.distance ?: 0
            )
        }
    }
}