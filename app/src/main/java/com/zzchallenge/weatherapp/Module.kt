package com.zzchallenge.weatherapp

import com.zzchallenge.weatherapp.ui.main.MainViewModel
import com.zzchallenge.weatherapp.ui.weatherDetails.WeatherDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val modules = module {

    viewModel { MainViewModel() }
    viewModel { WeatherDetailsViewModel() }
}