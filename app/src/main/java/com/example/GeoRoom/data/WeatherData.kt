package com.example.GeoRoom.data

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.flow.Flow

@Entity(
    tableName = "weather")
data class WeatherData(
    @PrimaryKey(true)
    var id: Long = 0,
    @SerializedName("temp")
    @ColumnInfo("temp")
    var temp: Float = 0.0f,
    @ColumnInfo("locationId")
    var locationId: Long = 0
)

data class WeatherResponse(
    @SerializedName("main")
    var weatherData: WeatherData? = null
)

@Dao
interface WeatherDao{
    @Insert
    abstract fun addNewWeather(weatherData: WeatherData)

    @Query("Select * from weather")
    abstract fun selectWeatherByLocation(): Flow<List<WeatherData>>
}
