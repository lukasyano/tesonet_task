package com.tesonet.task.network

import com.tesonet.task.network.model.ServersResponse
import com.tesonet.task.network.model.TokenResponse
import io.reactivex.Single
import retrofit2.http.*

interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("tokens")
    fun getToken(
        @Query("username") username: String,
        @Query("password") password: String
    ): Single<TokenResponse>

    @GET("servers")
    fun getServersList(@Header("Authorization") token : String)
    :Single<List<ServersResponse>>
}