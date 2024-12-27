package com.example.GeoRoom.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class Repository(
    private val locationDao: LocationDao,
    private val weatherDao: WeatherDao,
) {
    //location Dao functions
    suspend fun addLocation(locationData: LocationData) {
        locationDao.addLocation(locationData)
    }

    suspend fun selectLocations(): Flow<List<LocationData>> {
        return locationDao.selectAllLocations()
    }

    //weather Dao functions
    suspend fun addWeather(weatherData: WeatherData){
        weatherDao.addNewWeather(weatherData)
    }

    suspend fun selectWeathers(): Flow<List<WeatherData>>{
        return weatherDao.selectWeatherByLocation()
    }

    //combination of weather and location
    suspend fun getLastFiveLocationsWithWeather(): Flow<List<LocationWeatherData>> {
        val locationsFlow = locationDao.selectAllLocations()
        val weathersFlow = weatherDao.selectWeatherByLocation()

        return combine(locationsFlow, weathersFlow) { locations, weathers ->
            locations.takeLast(5).mapIndexed { index, location ->
                LocationWeatherData(
                    latitude = location.latitude,
                    longitude = location.longitude,
                    temperature = weathers.getOrNull(locations.size - index - 1)?.temp ?: 0.0f
                )
            }
        }
    }

    suspend fun getLocationsWithWeather(): Flow<List<LocationWeatherData>> {
        val locationsFlow = locationDao.selectAllLocations()
        val weathersFlow = weatherDao.selectWeatherByLocation()

        return combine(locationsFlow, weathersFlow) { locations, weathers ->
            locations.mapIndexed { index, location ->
                LocationWeatherData(
                    latitude = location.latitude,
                    longitude = location.longitude,
                    temperature = weathers.getOrNull(index)?.temp ?: 0.0f
                )
            }
        }
    }
}