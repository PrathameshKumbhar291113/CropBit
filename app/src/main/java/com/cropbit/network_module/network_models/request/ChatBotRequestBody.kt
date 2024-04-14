package com.cropbit.network_module.network_models.request

data class ChatBotRequestBody(
    val model: String = "gpt-3.5-turbo-instruct",
    val prompt: String,
    val maxTokens: Int = 1000,
    val temperature: Int = 0
)
