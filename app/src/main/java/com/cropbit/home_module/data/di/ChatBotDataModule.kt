package com.cropbit.home_module.data.di

import com.cropbit.home_module.data.repository.ChatBotRepoImpl
import com.cropbit.home_module.domain.repository.ChatBotRepository
import com.cropbit.network_module.chat_bot_api_service.ChatBotApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ChatBotDataModule {

    @Provides
    @Singleton
    fun providesChatBotRepositoryImpl(apiService: ChatBotApiService): ChatBotRepository {
        return ChatBotRepoImpl(apiService)
    }
}