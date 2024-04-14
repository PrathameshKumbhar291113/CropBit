package com.cropbit.home_module.domain.use_case

import com.cropbit.home_module.domain.repository.WeatherForecastRepository
import com.cropbit.network_module.network_models.request.WeatherForecastApiRequest
import com.cropbit.network_module.network_models.response.WeatherForecastApiResponse
import com.cropbit.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class WeatherForecastUseCase @Inject constructor(private val weatherForecastRepository: WeatherForecastRepository) {
    operator fun invoke(weatherForecastApiRequest: WeatherForecastApiRequest) = flow<NetworkResult<Response<WeatherForecastApiResponse>>> {
        emit(NetworkResult.Loading())
        emit(NetworkResult.Success(data = weatherForecastRepository.getWeatherForecast(weatherForecastApiRequest.apiKey, weatherForecastApiRequest.location, weatherForecastApiRequest.airQuality)))
    }.catch {
        emit(NetworkResult.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)
}