package com.tesonet.task.repository

import android.content.SharedPreferences
import com.tesonet.task.di.TOKEN_DEFAULT_VALUE
import com.tesonet.task.di.TOKEN_VALUE_KEY
import com.tesonet.task.network.ApiService
import com.tesonet.task.repository.entity.TokenEntity
import com.tesonet.task.repository.mapper.Mapper
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AuthRepository(
    private val sharedPreferences: SharedPreferences,
    private val apiService: ApiService
) {

    fun getToken(username: String, password: String): Single<TokenEntity> {
        return apiService.getToken(username, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { Mapper.mapTokenResponse(it) }
    }

    fun saveTokenToPersistent(token: String) {
        sharedPreferences.edit().putString(TOKEN_VALUE_KEY, token).apply()
    }
    
    fun getTokenFromPersistent(){
        val test = sharedPreferences.getString(TOKEN_VALUE_KEY, TOKEN_DEFAULT_VALUE)

    }
}