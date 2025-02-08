package com.cropbit

import com.cropbit.ai_chat_bot_module.GeminiRepository
import com.cropbit.network_module.chat_bot_api_service.ChatBotApiService
import com.cropbit.network_module.weather_forecast_api_service.WeatherForecastApiService
import com.cropbit.utils.ApiConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CropBitApplicationModule {
    @Provides
    @Singleton
    fun provideChatGptApiService() : ChatBotApiService {
        val client = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Authorization", "Bearer ${ApiConstants.CHAT_BOT_API_KEY}")
                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .build()

        return Retrofit.Builder()
            .baseUrl(ApiConstants.CHAT_BOT_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ChatBotApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherForecastApiService() : WeatherForecastApiService {
        return Retrofit.Builder()
            .baseUrl(ApiConstants.WEATHER_FORECAST_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherForecastApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideGeminiRepository(): GeminiRepository {
        return GeminiRepository()
    }


}