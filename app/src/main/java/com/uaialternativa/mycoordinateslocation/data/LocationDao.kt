package com.uaialternativa.mycoordinateslocation.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.uaialternativa.mycoordinateslocation.model.MyLocation

@Dao
interface LocationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addLocation(location: MyLocation)

    @Update
    suspend fun updateLocation(location: MyLocation)

    @Delete
    suspend fun deleteLocation(location: MyLocation)

    @Query("DELETE FROM locations_table")
    fun deleteAllLocations()

    @Query("SELECT * FROM locations_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<MyLocation>>

}