package com.example.GeoRoom.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.GeoRoom.data.LocationWeatherData
import com.example.GeoRoom.utils.Graph
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LocationWeatherViewModel : ViewModel() {
    private val _locationWeatherList = MutableStateFlow<List<LocationWeatherData>>(emptyList())
    val locationWeatherList = _locationWeatherList.asStateFlow()

    init {
        fetchLocationWeatherData()
    }

    private fun fetchLocationWeatherData() {
        viewModelScope.launch {
            Graph.repository.getLastFiveLocationsWithWeather().collect { locationWeathers ->
                _locationWeatherList.value = locationWeathers
            }
        }
    }
}