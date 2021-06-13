package com.zzchallenge.weatherapp.data.remote.model


import com.google.gson.annotations.SerializedName

data class Rain(
    @SerializedName("1h")
    val h: Double
)