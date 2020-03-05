package com.octacore.gideonchukwu.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.octacore.gideonchukwu.model.RemoteModel.Filters
import com.octacore.gideonchukwu.repository.FiltersRepository
import kotlinx.coroutines.Dispatchers

class FilterActivityViewModel {
    private val repository = FiltersRepository()

    val data: LiveData<Filters> = liveData(Dispatchers.IO){
        val retrievedData = repository.loadAllFilters()
        emit(retrievedData)
    }
}