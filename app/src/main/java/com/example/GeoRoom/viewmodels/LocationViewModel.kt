package com.example.GeoRoom.viewmodels

import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.GeoRoom.data.LocationData
import com.example.GeoRoom.utils.Graph
import com.example.GeoRoom.utils.LocationUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LocationViewModel: ViewModel() {
    private val _userLocationData = MutableStateFlow<LocationData?>(null)
    val userLocationData = _userLocationData.asStateFlow()

    private val _listOfLocations = MutableStateFlow(listOf<LocationData>())
    val listOfLocations = _listOfLocations.asStateFlow()

    fun updateUserLocation(lastLocation: Location?) {
        if(lastLocation != null){
            _userLocationData.value = LocationData(latitude = lastLocation.latitude, longitude = lastLocation.longitude)
        }
    }

    fun getLocation(locationUtils: LocationUtils){
        locationUtils.getLocation()
    }

    fun addLocation(locationData: LocationData){
        viewModelScope.launch(
            Dispatchers.IO
        ){
            Graph.repository.addLocation(locationData)
        }
    }
}