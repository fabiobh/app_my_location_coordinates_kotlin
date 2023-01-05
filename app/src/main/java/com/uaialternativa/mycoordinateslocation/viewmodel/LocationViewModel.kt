package com.uaialternativa.mycoordinateslocation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.uaialternativa.mycoordinateslocation.data.LocationDatabase
import com.uaialternativa.mycoordinateslocation.repository.LocationRepository
import com.uaialternativa.mycoordinateslocation.model.MyLocation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LocationViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<MyLocation>>
    private val repository: LocationRepository

    init {
        val locationDao = LocationDatabase.getDatabase(application).locationDao()
        repository = LocationRepository(locationDao)
        readAllData = repository.readAllData
    }

    fun addLocation(location: MyLocation) {
        viewModelScope.launch ( Dispatchers.IO ) {
            repository.addLocation(location)
        }
    }

    fun updateLocation(location: MyLocation) {
        viewModelScope.launch ( Dispatchers.IO ) {
            repository.updateLocation(location)
        }
    }

    //com.uaialternativa.mycoordinateslocation.data.model.User
    fun deleteLocation(location: MyLocation) {
        viewModelScope.launch ( Dispatchers.IO ) {
            repository.deleteLocation(location)
        }
    }

    fun deleteAllLocations() {
        viewModelScope.launch ( Dispatchers.IO ) {
            repository.deleteAllLocations()
        }
    }

}