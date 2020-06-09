package com.tesonet.task.repository

import com.tesonet.task.network.ApiService
import com.tesonet.task.repository.entity.TokenEntity
import com.tesonet.task.repository.mapper.Mapper
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AuthRepository(
    private val apiService: ApiService
) {
    fun getToken(username: String, password: String): Single<TokenEntity> {
        return apiService.getToken(username, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { Mapper.mapTokenResponse(it) }
    }
}