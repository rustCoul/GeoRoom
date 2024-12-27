package com.example.GeoRoom.screens

import android.Manifest
import android.annotation.SuppressLint
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.GeoRoom.data.LocationData
import com.example.GeoRoom.data.WeatherData
import com.example.GeoRoom.utils.LocationUtils
import com.example.GeoRoom.viewmodels.LocationViewModel
import com.example.GeoRoom.viewmodels.WeatherViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    locationViewModel: LocationViewModel,
    weatherViewModel: WeatherViewModel,
    locationUtils: LocationUtils,
    navHostController: NavHostController
) {
    Scaffold(
        content = { innerPadding ->
            MainContent(
                Modifier.padding(innerPadding),
                locationViewModel,
                weatherViewModel,
                locationUtils
            )
        },
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(Color.LightGray),
                title = {
                    Text(
                        "MainScreen"
                    )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navHostController.navigate(Graph.SECOND_SCREEN)
                },
                containerColor = Color.LightGray
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.List,
                    contentDescription = " "
                )
            }
        }
    )
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun MainContent(
    modifier: Modifier,
    locationViewModel: LocationViewModel,
    weatherViewModel: WeatherViewModel,
    locationUtils: LocationUtils,
) {
    val userLocationData by locationViewModel.userLocationData.collectAsState()
    val temp by weatherViewModel.temp.collectAsState()
    locationViewModel.updateUserLocation(lastLocation = null)

    val requestLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) {
        if (it[Manifest.permission.ACCESS_COARSE_LOCATION] == true && it[Manifest.permission.ACCESS_FINE_LOCATION] == true) {
            locationViewModel.getLocation(locationUtils)

        }
    }

    LaunchedEffect(key1 = Unit, key2 = userLocationData) {
        requestLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
        userLocationData?.let {
            weatherViewModel.getWeatherResponse(it.latitude, it.longitude)
        }
    }


    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            if (userLocationData != null) {
                userLocationData?.latitude.toString()
            } else {
                "No location available"
            }
        )
        Spacer(Modifier.height(10.dp))
        Text(
            temp.toString()
        )
        Spacer(Modifier.height(10.dp))
        Button(
            onClick = {
                locationViewModel.addLocation(
                    LocationData(
                        latitude = userLocationData!!.latitude,
                        longitude = userLocationData!!.longitude
                    )
                )
                weatherViewModel.addWeather(
                    WeatherData(
                        temp = temp!!
                    )
                )
            }
        ) {
            Text(
                "Save location"
            )
        }
        //Log.d("Location", userLocationData!!.latitude.toString() + userLocationData!!.longitude.toString())
    }

}