package com.octacore.gideonchukwu.repository

import android.util.Log
import com.google.gson.Gson
import com.octacore.gideonchukwu.network.FiltersService.createService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FiltersRepository {
    private val TAG = this::class.java.simpleName
    val service = createService()

    suspend fun loadAllFilters() = service.getFiltersList()

    /*fun loadAllFilters(){
        GlobalScope.launch() {
            val request = service.getFiltersList()
            try{
                val response = request.await()
                if (response.isSuccessful){
                    val body = response.body()
                    Log.i(TAG, Gson().toJson(body))
                } else{
                    Log.i(TAG, response.message())
                }
            } catch (err: Exception){
                throw Exception("An error occurred while fetching data")
            }
        }
    }*/
}