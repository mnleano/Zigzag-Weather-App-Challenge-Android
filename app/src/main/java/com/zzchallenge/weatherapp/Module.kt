package com.zzchallenge.weatherapp

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.zzchallenge.weatherapp.data.remote.WeatherService
import com.zzchallenge.weatherapp.infrastructure.NetworkInterceptor
import com.zzchallenge.weatherapp.ui.main.MainViewModel
import com.zzchallenge.weatherapp.ui.weatherDetails.WeatherDetailsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


private fun initOkHttpClient(interceptor: NetworkInterceptor): OkHttpClient {
    val okHttpClientBuilder = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .addInterceptor(
            HttpLoggingInterceptor().setLevel(
                if (BuildConfig.DEBUG)
                    HttpLoggingInterceptor.Level.BODY
                else HttpLoggingInterceptor.Level.NONE
            )
        )
        .connectTimeout(60, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
    return okHttpClientBuilder.build()
}

fun initGson(): Gson = GsonBuilder().setLenient().create()

fun initCoroutineScope(): CoroutineScope =
    CoroutineScope(Dispatchers.IO + SupervisorJob())


val modules = module {

    single { Gson() }

    // Remote - Web service
    single { initCoroutineScope() }
    single { NetworkInterceptor(get()) }
    single { initOkHttpClient(get()) }
    single {
        Retrofit
            .Builder()
            .client(get())
            .addConverterFactory(GsonConverterFactory.create(initGson()))
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }

    single {get<Retrofit>().create(WeatherService::class.java) as WeatherService}

    viewModel { MainViewModel() }
    viewModel { WeatherDetailsViewModel() }
}