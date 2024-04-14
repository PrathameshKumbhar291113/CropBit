package com.cropbit.home_module.presentation.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cropbit.home_module.domain.use_case.ChatBotUseCase
import com.cropbit.network_module.network_models.response.ChatBotPromptResponse
import com.cropbit.utils.NetworkResult
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class BitBotViewModel @Inject constructor(
    private val chatBotUseCase: ChatBotUseCase
) : ViewModel() {

    private val _chatBotResponse =
        MutableLiveData<NetworkResult<Response<ChatBotPromptResponse>>>()
    val chatBotResponse: LiveData<NetworkResult<Response<ChatBotPromptResponse>>> get() = _chatBotResponse

    fun postChatBotPrompt(chatBotRequestBody: JsonObject){

        chatBotUseCase(requestBody = chatBotRequestBody).onEach {
            when (it) {
                is NetworkResult.Loading -> {}
                is NetworkResult.Success -> {
                    _chatBotResponse.postValue(it)
                    Log.e("response", "postChatBotPrompt SUCCESS: ${chatBotRequestBody.toString()}")
                    Log.e("response", "postChatBotPrompt SUCCESS: ${_chatBotResponse.value?.data?.body()?.choices?.first()?.text.toString()}")
                    Log.e("response", "postChatBotPrompt SUCCESS:  ${it.data?.body()?.choices?.get(0)} ------- ${it.data?.errorBody().toString()}")
                }
                is NetworkResult.Error -> {
                    Log.e("response", "postChatBotPrompt ERROR: ${chatBotRequestBody.toString()}")
                    Log.e("response", "postChatBotPrompt ERROR: ${it.data?.body().toString()} ------- ${it.data?.message().toString()}")
                }
            }
        }.launchIn(viewModelScope)
    }
}