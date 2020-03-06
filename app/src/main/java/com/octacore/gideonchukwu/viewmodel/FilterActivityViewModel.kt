package com.octacore.gideonchukwu.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.octacore.gideonchukwu.model.Model.Filters
import com.octacore.gideonchukwu.repository.FiltersRepository
import kotlinx.coroutines.Dispatchers

class FilterActivityViewModel : ViewModel() {
    private val repository = FiltersRepository()

    val data: LiveData<List<Filters>> = liveData(Dispatchers.IO){
        val retrievedData = repository.loadAllFilters()
        emit(retrievedData)
    }
}