package com.octacore.gideonchukwu.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.octacore.gideonchukwu.model.Model.CarOwners
import com.octacore.gideonchukwu.repository.FiltersRepository
import kotlinx.coroutines.Dispatchers

class CarOwnerActivityViewModel:ViewModel() {
    private val repository = FiltersRepository()

    val data : LiveData<List<CarOwners>> = liveData(Dispatchers.IO) {
        val retrievedData = repository.loadAllCarOwners()
        emit(retrievedData)
    }
}