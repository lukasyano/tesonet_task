package com.tesonet.task.repository

import android.content.SharedPreferences
import android.util.Log
import com.tesonet.task.network.ApiService
import com.tesonet.task.repository.entity.TokenEntity
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AuthRepository(
    private val sharedPreferences: SharedPreferences,
    private val apiService: ApiService
) {

    fun getToken(username: String, password: String): Single<TokenEntity>? {
     val singleton = apiService.getToken(username, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
         .subscribe(
             {
                 Log.d("asd" , it.message + "   " + it.token)
             },{
                 Log.d("asd", it.message.toString())
             }
         )
        return null
    }
}