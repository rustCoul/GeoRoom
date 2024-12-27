package com.example.GeoRoom.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.GeoRoom.viewmodels.LocationWeatherViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SecondScreen(
    locationWeatherViewModel: LocationWeatherViewModel = viewModel()
) {
    val locationWeatherList by locationWeatherViewModel.locationWeatherList.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Saved Locations and Weather")
                },
                colors = TopAppBarDefaults.topAppBarColors(Color.LightGray)
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues)
        ) {
            items(locationWeatherList) { locationWeather ->
                LocationWeatherItem(
                    latitude = locationWeather.latitude ?: 0.0,
                    longitude = locationWeather.longitude ?: 0.0,
                    temperature = locationWeather.temperature
                )
            }
        }
    }
}

@Composable
fun LocationWeatherItem(
    latitude: Double,
    longitude: Double,
    temperature: Float
) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Location Details",
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text("Latitude: $latitude")
            Text("Longitude: $longitude")
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Temperature: $temperature °C",
                fontSize = 16.sp
            )
        }
    }
}