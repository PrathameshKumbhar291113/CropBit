package com.cropbit.network_module.network_models.request

data class WeatherForecastApiRequest(
    val apiKey : String,
    val location: String,
    val airQuality: String
)
