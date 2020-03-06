package com.octacore.gideonchukwu.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object FiltersService {
    private const val BASE_URL = "https://android-json-test-api.herokuapp.com/"

    fun createService(): FiltersAPI = getRetrofitService().create(FiltersAPI::class.java)

    private fun getRetrofitService() : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .client(getClient())
        .build()

    private fun getClient() : OkHttpClient{
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }
}