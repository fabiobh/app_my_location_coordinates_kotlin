package com.uaialternativa.mycoordinateslocation.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "locations_table")
data class MyLocation (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val latitude: String,
    val longitude: String,
    val placeName: String,
): Parcelable