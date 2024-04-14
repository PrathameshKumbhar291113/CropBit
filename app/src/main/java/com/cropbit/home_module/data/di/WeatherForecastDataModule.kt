package com.cropbit.home_module.data.di

import com.cropbit.home_module.data.repository.WeatherForecastRepoImpl
import com.cropbit.home_module.domain.repository.WeatherForecastRepository
import com.cropbit.network_module.weather_forecast_api_service.WeatherForecastApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object WeatherForecastDataModule {

    @Provides
    @Singleton
    fun providesWeatherForecastRepositoryImpl(apiService: WeatherForecastApiService): WeatherForecastRepository {
        return WeatherForecastRepoImpl(apiService)
    }

}