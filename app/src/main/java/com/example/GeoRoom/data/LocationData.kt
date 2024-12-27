package com.example.GeoRoom.data

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Entity("Location")
data class LocationData(
    @PrimaryKey(true)
    var id: Long = 0,
    @ColumnInfo("latitude")
    var latitude: Double?,
    @ColumnInfo("longitude")
    var longitude: Double?
)

@Dao
interface LocationDao{
    @Insert
    abstract fun addLocation(locationData: LocationData)

    @Query("Select * from Location")
    abstract fun selectAllLocations(): Flow<List<LocationData>>
}
