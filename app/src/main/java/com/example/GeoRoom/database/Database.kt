package com.example.GeoRoom.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.GeoRoom.data.LocationDao
import com.example.GeoRoom.data.LocationData
import com.example.GeoRoom.data.WeatherDao
import com.example.GeoRoom.data.WeatherData

@Database(
    entities = [LocationData::class, WeatherData::class],
    exportSchema = false,
    version = 1
)
abstract class Database: RoomDatabase(){
    abstract fun getLocationDao(): LocationDao
    abstract fun getWeatherDao(): WeatherDao
}
