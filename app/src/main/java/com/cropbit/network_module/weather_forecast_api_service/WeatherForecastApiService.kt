package com.cropbit.network_module.weather_forecast_api_service

import com.cropbit.network_module.network_models.response.WeatherForecastApiResponse
import com.cropbit.utils.ApiConstants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherForecastApiService {

    @GET(ApiConstants.WEATHER_FORECAST_END_POINT)
    suspend fun getWeatherForecast(
        @Query("key") apiKey: String,
        @Query("q") location: String,
        @Query("aqi") airQualityData: String
    ): Response<WeatherForecastApiResponse>

}