package com.cropbit.home_module.domain.use_case

import com.cropbit.home_module.domain.repository.ChatBotRepository
import com.cropbit.network_module.network_models.response.ChatBotPromptResponse
import com.cropbit.utils.NetworkResult
import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class ChatBotUseCase  @Inject constructor(private val chatBotRepository: ChatBotRepository) {
    operator fun invoke(requestBody: JsonObject) = flow<NetworkResult<Response<ChatBotPromptResponse>>> {
        emit(NetworkResult.Loading())
        emit(NetworkResult.Success(data = chatBotRepository.postChatGpPrompt(requestBody)))
    }.catch {
        emit(NetworkResult.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)
}