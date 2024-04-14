package com.cropbit.home_module.data.repository

import com.cropbit.home_module.domain.repository.WeatherForecastRepository
import com.cropbit.network_module.network_models.response.WeatherForecastApiResponse
import com.cropbit.network_module.weather_forecast_api_service.WeatherForecastApiService
import retrofit2.Response
import javax.inject.Inject

class WeatherForecastRepoImpl @Inject constructor(private val apiService: WeatherForecastApiService) :
    WeatherForecastRepository {
    override suspend fun getWeatherForecast(
        apiKey: String,
        location: String,
        airQualityData: String
    ): Response<WeatherForecastApiResponse> {
        return apiService.getWeatherForecast(apiKey, location, airQualityData)
    }
}