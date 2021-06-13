package com.zzchallenge.weatherapp.data.remote

import com.zzchallenge.weatherapp.BuildConfig
import com.zzchallenge.weatherapp.data.remote.model.WeatherResponse
import com.zzchallenge.weatherapp.data.repositories.WeatherRepository
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("onecall?units=metric&appid=${BuildConfig.OPEN_WEATHER_API_APPID}")
    suspend fun getWeather(
        @Query("lat") lat: Double,
        @Query("long") long: Double,
    ): Response<WeatherResponse>
}