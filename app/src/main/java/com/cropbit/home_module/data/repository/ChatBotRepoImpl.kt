package com.cropbit.home_module.data.repository


import com.cropbit.home_module.domain.repository.ChatBotRepository
import com.cropbit.network_module.chat_bot_api_service.ChatBotApiService
import com.cropbit.network_module.network_models.response.ChatBotPromptResponse
import com.google.gson.JsonObject
import retrofit2.Response
import javax.inject.Inject

class ChatBotRepoImpl @Inject constructor(private val apiService: ChatBotApiService) :
    ChatBotRepository {
    override suspend fun postChatGpPrompt(requestBody: JsonObject): Response<ChatBotPromptResponse> {
        return apiService.postChatGpPrompt(requestBody)
    }

}