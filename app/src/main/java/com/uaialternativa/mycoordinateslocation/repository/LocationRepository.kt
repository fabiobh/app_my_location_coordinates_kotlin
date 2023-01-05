package com.uaialternativa.mycoordinateslocation.repository

import androidx.lifecycle.LiveData
import com.uaialternativa.mycoordinateslocation.data.LocationDao
import com.uaialternativa.mycoordinateslocation.model.MyLocation

class LocationRepository(private val userDao: LocationDao) {

    val readAllData: LiveData<List<MyLocation>> = userDao.readAllData()

    suspend fun addLocation(user: MyLocation) {
        userDao.addLocation(user)
    }

    suspend fun updateLocation(user: MyLocation) {
        userDao.updateLocation(user)
    }

    suspend fun deleteLocation(user: MyLocation) {
        userDao.deleteLocation(user)
    }

    suspend fun deleteAllLocations() {
        userDao.deleteAllLocations()
    }

}