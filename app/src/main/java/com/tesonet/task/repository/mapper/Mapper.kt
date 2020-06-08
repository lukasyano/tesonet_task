package com.tesonet.task.repository.mapper

import com.tesonet.task.network.model.TokenResponse
import com.tesonet.task.repository.entity.TokenEntity

object Mapper {

    fun mapTokenResponse(result: TokenResponse): TokenEntity {

        return TokenEntity(
            message = result.message,
            token = result.token
        )
    }
}