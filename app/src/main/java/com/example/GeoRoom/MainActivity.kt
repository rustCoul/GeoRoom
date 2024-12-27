package com.example.GeoRoom

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.GeoRoom.screens.Navigation
import com.example.GeoRoom.ui.theme.GeoRetrofitRoomComposeNavigationTheme
import com.example.GeoRoom.utils.LocationUtils
import com.example.GeoRoom.viewmodels.LocationViewModel
import com.example.GeoRoom.viewmodels.LocationWeatherViewModel
import com.example.GeoRoom.viewmodels.WeatherViewModel

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val locationViewModel: LocationViewModel = viewModel()
            val weatherViewModel: WeatherViewModel = viewModel()
            val locationWeatherViewModel: LocationWeatherViewModel = viewModel()
            val locationUtils = LocationUtils(context = LocalContext.current, locationViewModel)

            GeoRetrofitRoomComposeNavigationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    Navigation(locationViewModel, weatherViewModel, locationWeatherViewModel)
                }
            }
        }
    }
}

