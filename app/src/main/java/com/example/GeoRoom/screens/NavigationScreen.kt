package com.example.GeoRoom.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.GeoRoom.utils.LocationUtils
import com.example.GeoRoom.viewmodels.LocationViewModel
import com.example.GeoRoom.viewmodels.LocationWeatherViewModel
import com.example.GeoRoom.viewmodels.WeatherViewModel

@Composable
fun Navigation(
    locationViewModel: LocationViewModel,
    weatherViewModel: WeatherViewModel,
    locationWeatherViewModel: LocationWeatherViewModel
){
    val navController = rememberNavController()
    val context = LocalContext.current
    val locationUtils = LocationUtils(context, locationViewModel)

    NavHost(
        navController = navController,
        startDestination = Graph.MAIN_SCREEN
    ){
        composable(route = Graph.MAIN_SCREEN) {
            MainScreen(locationViewModel, weatherViewModel, locationUtils, navController)
        }
        composable(route = Graph.SECOND_SCREEN) {
            SecondScreen()
        }
    }
}

object Graph{
    const val MAIN_SCREEN = "MainScreen"
    const val SECOND_SCREEN = "SecondScreen"
}