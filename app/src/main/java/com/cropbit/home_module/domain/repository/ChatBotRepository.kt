package com.cropbit.home_module.domain.repository

import com.cropbit.network_module.network_models.response.ChatBotPromptResponse
import com.google.gson.JsonObject
import retrofit2.Response

interface ChatBotRepository {
    suspend fun postChatGpPrompt(requestBody: JsonObject): Response<ChatBotPromptResponse>
}