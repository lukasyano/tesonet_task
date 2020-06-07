package com.tesonet.task.network

import com.tesonet.task.network.model.TokenResponse
import io.reactivex.Single
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("tokens")
    fun getToken(
        @Query("username") username: String,
        @Query("password") password: String
    ): Single<TokenResponse>
}