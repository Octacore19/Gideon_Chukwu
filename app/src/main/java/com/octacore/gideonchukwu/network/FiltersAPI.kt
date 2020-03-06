package com.octacore.gideonchukwu.network

import com.octacore.gideonchukwu.model.Model.Filters
import retrofit2.http.GET

interface FiltersAPI {

    @GET("/accounts")
    suspend fun getFiltersList() : List<Filters>
}