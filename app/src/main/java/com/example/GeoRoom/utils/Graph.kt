package com.example.GeoRoom.utils

import android.content.Context
import androidx.room.Room
import com.example.GeoRoom.data.Repository
import com.example.GeoRoom.database.Database

object Graph {
    private lateinit var database: com.example.GeoRoom.database.Database

    val repository by lazy {
        Repository(
            locationDao = database.getLocationDao(),
            weatherDao = database.getWeatherDao()
        )
    }

    fun provide(context: Context){
        this.database = Room.databaseBuilder(context, Database::class.java, "weatherByLocation.db").build()
    }
}