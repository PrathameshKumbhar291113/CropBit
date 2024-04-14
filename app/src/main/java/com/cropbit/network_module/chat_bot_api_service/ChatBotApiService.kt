package com.cropbit.network_module.chat_bot_api_service

import com.cropbit.network_module.network_models.response.ChatBotPromptResponse
import com.cropbit.utils.ApiConstants
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ChatBotApiService {
    @POST(ApiConstants.CHAT_BOT_END_POINT)
    suspend fun postChatGpPrompt(@Body requestBody: JsonObject): Response<ChatBotPromptResponse>
}