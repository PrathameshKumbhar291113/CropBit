package com.cropbit.network_module.network_models.request

import com.cropbit.network_module.network_models.response.ChatBotPromptResponse

data class ChatBotRequestBody(
    val model: String = "gpt-4o",
    val messages: List<ChatBotPromptResponse.Message>,
    val maxTokens: Int = 1024
)
