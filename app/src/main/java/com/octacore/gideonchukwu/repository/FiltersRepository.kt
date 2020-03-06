package com.octacore.gideonchukwu.repository

import android.os.Environment
import com.octacore.gideonchukwu.model.Model.CarOwners
import com.octacore.gideonchukwu.model.Model.Filters
import com.octacore.gideonchukwu.network.FiltersService.createService
import com.opencsv.CSVReader
import com.opencsv.bean.CsvToBean
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileNotFoundException
import java.io.FileReader

class FiltersRepository {
    private val service = createService()

    suspend fun loadAllFilters(): List<Filters> {
        try {
            return service.getFiltersList()
        } catch (err: Exception) {
            throw Exception("An error occurred")
        }
    }

    suspend fun loadAllCarOwners(): List<CarOwners> {
        val data = mutableListOf<CarOwners>()
        withContext(Dispatchers.IO) {
            try {
                val decagon =
                    File(Environment.getExternalStorageDirectory(), "decagon/car_ownsers_data.csv")
                val reader = CSVReader(FileReader(decagon.absolutePath))
                reader.skip(1)
                reader.readAll().forEach { item ->
                    val owner = CarOwners(
                        item[0],
                        item[1],
                        item[2],
                        item[3],
                        item[4],
                        item[5],
                        item[6],
                        item[7],
                        item[8],
                        item[9],
                        item[10]
                    )
                    data.add(owner)
                }
            } catch (err: FileNotFoundException) {
                throw FileNotFoundException("The File is not found!")
            }
        }
        return data
    }
}