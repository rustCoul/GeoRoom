package com.example.GeoRoom.utils

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Looper
import androidx.core.app.ActivityCompat
import com.example.GeoRoom.viewmodels.LocationViewModel
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices

class LocationUtils(
    private val context: Context,
    private val locationViewModel: LocationViewModel
){
    private val _fusedProvider = LocationServices.getFusedLocationProviderClient(context)
    private val locationRequest = LocationRequest.Builder(1000).build()

    private val userLocationCallback = object : LocationCallback(){
        override fun onLocationResult(location: LocationResult) {
            super.onLocationResult(location)
            locationViewModel.updateUserLocation(location.lastLocation)
        }
    }

    private fun isLocationPermissionGranted(): Boolean{
        return ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    @SuppressLint("MissingPermission")
    fun getLocation(){
        if(isLocationPermissionGranted()){
            _fusedProvider.requestLocationUpdates(
                locationRequest, userLocationCallback, Looper.getMainLooper()
            )
        }
    }
}