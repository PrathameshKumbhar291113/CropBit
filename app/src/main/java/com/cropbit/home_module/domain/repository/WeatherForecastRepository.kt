package com.cropbit.home_module.domain.repository

import com.cropbit.network_module.network_models.response.WeatherForecastApiResponse
import retrofit2.Response
import retrofit2.http.Query

interface WeatherForecastRepository {

    suspend fun getWeatherForecast(
        @Query("key") apiKey: String,
        @Query("q") location: String,
        @Query("aqi") airQualityData: String
    ): Response<WeatherForecastApiResponse>
}