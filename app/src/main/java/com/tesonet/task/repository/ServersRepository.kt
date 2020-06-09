package com.tesonet.task.repository

import com.tesonet.task.network.ApiService
import com.tesonet.task.repository.entity.ServersEntity
import com.tesonet.task.repository.mapper.Mapper
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ServersRepository(
    private val apiService: ApiService
) {

    fun getServersFromApi(token: String): Single<List<ServersEntity>> {

        return apiService.getServersList(token)
            .subscribeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .map { Mapper.mapServersListResponse(it) }
    }
}
