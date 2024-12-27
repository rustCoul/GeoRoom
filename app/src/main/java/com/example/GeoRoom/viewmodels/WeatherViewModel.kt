package com.example.GeoRoom.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.GeoRoom.data.LocationData
import com.example.GeoRoom.data.WeatherData
import com.example.GeoRoom.data.WeatherResponse
import com.example.GeoRoom.retrofit_and_api_services.RetrofitClient
import com.example.GeoRoom.utils.Graph
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WeatherViewModel: ViewModel() {

    private val _temp = MutableStateFlow(WeatherResponse().weatherData?.temp)
    val temp = _temp.asStateFlow()

    private val _locationData = MutableStateFlow<LocationData?>(null)
    val locationData = _locationData.asStateFlow()

    private val _listOfWeathers = MutableStateFlow(listOf<WeatherData>())
    val listOfWeathers = _listOfWeathers.asStateFlow()

    fun getWeatherResponse(latitude: Double?, longitude: Double?){
        viewModelScope.launch(
            Dispatchers.IO
        ){
            _temp.value = RetrofitClient.weatherAPI.getWeatherData(lat =  latitude, lon = longitude, appId = "c8ba2a68966767ec165a2dd050ecbc7f", units = "metric").weatherData?.temp
        }
    }

    fun addWeather(weatherData: WeatherData){
        viewModelScope.launch(
            Dispatchers.IO
        ) {
            Graph.repository.addWeather(weatherData)
        }
    }
}