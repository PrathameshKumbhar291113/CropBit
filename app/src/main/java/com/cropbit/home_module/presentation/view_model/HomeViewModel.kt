package com.cropbit.home_module.presentation.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cropbit.home_module.domain.use_case.WeatherForecastUseCase
import com.cropbit.network_module.network_models.request.WeatherForecastApiRequest
import com.cropbit.network_module.network_models.response.WeatherForecastApiResponse
import com.cropbit.utils.ApiConstants
import com.cropbit.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val weatherForecastUseCase: WeatherForecastUseCase
) : ViewModel(){

    private val _currentWeatherResponse =
        MutableLiveData<NetworkResult<Response<WeatherForecastApiResponse>>>()
    val currentWeatherResponse: LiveData<NetworkResult<Response<WeatherForecastApiResponse>>> get() = _currentWeatherResponse

    var latitude = MutableLiveData<String?>(null)
    var longitude = MutableLiveData<String?>(null)


    fun getCurrentWeatherForecast(latitude: Double, longitude: Double) {

        val weatherForecastApiRequest: WeatherForecastApiRequest = WeatherForecastApiRequest(
            apiKey = ApiConstants.WEATHER_FORECAST_API_KEY,
            location = "$latitude,$longitude",
            airQuality = "yes"
        )

        Log.e("weather_api", "getCurrentWeatherForecast request: ${weatherForecastApiRequest.toString()}")

        weatherForecastUseCase(weatherForecastApiRequest).onEach {
            when (it) {
                is NetworkResult.Loading -> {}
                is NetworkResult.Success -> {
                    _currentWeatherResponse.postValue(it)
                    Log.e("weather_api", "getCurrentWeatherForecast response: ${it.data?.body().toString()}")
                }
                is NetworkResult.Error -> {

                }
            }
        }.launchIn(viewModelScope)
    }


}