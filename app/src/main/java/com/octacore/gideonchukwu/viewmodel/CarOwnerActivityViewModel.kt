package com.octacore.gideonchukwu.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.google.gson.Gson
import com.octacore.gideonchukwu.model.Model.CarOwners
import com.octacore.gideonchukwu.model.Model.Filters
import com.octacore.gideonchukwu.repository.FiltersRepository
import kotlinx.coroutines.Dispatchers

class CarOwnerActivityViewModel:ViewModel() {
    private val repository = FiltersRepository()

    val data : LiveData<List<CarOwners>> = liveData(Dispatchers.IO) {
        val retrievedData = repository.loadAllCarOwners()
        emit(retrievedData)
    }

    fun filterByName(filter: Filters, carOwnersList: List<CarOwners>) : LiveData<List<CarOwners>>{
        val name = filter.fullName!!.split(" ")
        val firstName = name[0]
        val lastName = name[1]
        val data = carOwnersList.filter { (firstName == (it.firstName!!))
                || (firstName == (it.lastName!!))
                || (lastName == (it.firstName))
                || (lastName == (it.lastName))}

        return liveData {
            val retrievedData = data
            emit(retrievedData)
        }
    }

    fun filterByDate(filter: Filters, carOwnersList: List<CarOwners>) : LiveData<List<CarOwners>>{
        val data = carOwnersList.filter { it.carModelYear!! in filter.createdAt!! }

        return liveData {
            val retrievedData = data
            emit(retrievedData)
        }
    }

    fun filterByGender(filter: Filters, carOwnersList: List<CarOwners>) : LiveData<List<CarOwners>>{
        val data = carOwnersList.filter { filter.gender!!.toLowerCase() == it.gender!!.toLowerCase() }

        return liveData {
            val retrievedData = data
            emit(retrievedData)
        }
    }

    fun filterByColour(filter: Filters, carOwnersList: List<CarOwners>) : LiveData<List<CarOwners>>{
        val data = carOwnersList.filter { owner -> owner.carColor in filter.colors!!.map { it } }

        return liveData {
            val retrievedData = data
            emit(retrievedData)
        }
    }

    fun filterByCountry(filter: Filters, carOwnersList: List<CarOwners>) : LiveData<List<CarOwners>>{
        val data = carOwnersList.filter { owner -> owner.country in filter.countries!!.map { it } }

        return liveData {
            val retrievedData = data
            emit(retrievedData)
        }
    }
}