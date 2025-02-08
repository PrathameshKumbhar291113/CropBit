package com.cropbit.ai_chat_bot_module

import android.util.Log
import com.cropbit.utils.ApiConstants
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GeminiRepository() {
    private val generativeModel =
        GenerativeModel(modelName = "gemini-pro", apiKey = ApiConstants.GEMINI_API_KEY)

    suspend fun generateText(prompt: String): String {
        return withContext(Dispatchers.IO) {
            try {
                val response = generativeModel.generateContent(prompt)
                response.text ?: "No response from AI"
            } catch (e: Exception) {
                Log.e("GeminiRepository", "Error: ${e.message}")
                "Error fetching response"
            }
        }
    }
}