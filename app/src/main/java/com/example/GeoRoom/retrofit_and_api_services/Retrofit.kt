package com.example.GeoRoom.retrofit_and_api_services

import com.example.GeoRoom.data.WeatherResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    @GET("weather")
    suspend fun getWeatherData(@Query("lat")lat: Double?, @Query("lon") lon: Double?, @Query("appid") appId: String, @Query("units") units: String): WeatherResponse
}

object RetrofitClient{
    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    private fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }
    val weatherAPI: WeatherAPI by lazy {
        getRetrofitInstance().create(WeatherAPI::class.java)
    }
}