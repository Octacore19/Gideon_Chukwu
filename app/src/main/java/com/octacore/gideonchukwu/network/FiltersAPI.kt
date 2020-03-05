package com.octacore.gideonchukwu.network

import com.octacore.gideonchukwu.model.RemoteModel.Filters
import retrofit2.Response
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface FiltersAPI {
    /*@GET("/accounts")
    fun getFiltersList() : Deferred<Response<Filters>>*/

    @GET("/accounts")
    suspend fun getFiltersList() : Filters
}