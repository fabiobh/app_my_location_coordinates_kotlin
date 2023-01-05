package com.uaialternativa.mycoordinateslocation.repository

import androidx.lifecycle.LiveData
import com.uaialternativa.mycoordinateslocation.data.LocationDao
import com.uaialternativa.mycoordinateslocation.model.MyLocation

class LocationRepository(private val locationDao: LocationDao) {

    val readAllData: LiveData<List<MyLocation>> = locationDao.readAllData()

    suspend fun addLocation(location: MyLocation) {
        locationDao.addLocation(location)
    }

    suspend fun updateLocation(location: MyLocation) {
        locationDao.updateLocation(location)
    }

    suspend fun deleteLocation(location: MyLocation) {
        locationDao.deleteLocation(location)
    }

    suspend fun deleteAllLocations() {
        locationDao.deleteAllLocations()
    }

}