package com.cropbit.home_module.domain.di

import com.cropbit.home_module.domain.repository.WeatherForecastRepository
import com.cropbit.home_module.domain.use_case.WeatherForecastUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object WeatherForecastDomainModule {
    @Provides
    @Singleton
    fun providesWeatherForecastRepository(
        weatherForecastRepository: WeatherForecastRepository
    ): WeatherForecastUseCase {
        return WeatherForecastUseCase(weatherForecastRepository)
    }
}