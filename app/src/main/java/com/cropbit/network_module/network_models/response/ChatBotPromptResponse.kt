package com.cropbit.network_module.network_models.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChatBotPromptResponse(
    @SerializedName("choices")
    var choices: List<Choice?>?,
    @SerializedName("created")
    var created: Int?,
    @SerializedName("id")
    var id: String?,
    @SerializedName("model")
    var model: String?,
    @SerializedName("object")
    var objectX: String?,
    @SerializedName("usage")
    var usage: Usage?
) : Parcelable {
    @Parcelize
    data class Choice(
        @SerializedName("finish_reason")
        var finishReason: String?,
        @SerializedName("index")
        var index: Int?,
        @SerializedName("message") // Instead of "text", GPT-4o uses "message"
        var message: Message?
    ) : Parcelable

    @Parcelize
    data class Message( // New message structure
        @SerializedName("role")
        var role: String?,
        @SerializedName("content")
        var content: String?
    ) : Parcelable

    @Parcelize
    data class Usage(
        @SerializedName("completion_tokens")
        var completionTokens: Int?,
        @SerializedName("prompt_tokens")
        var promptTokens: Int?,
        @SerializedName("total_tokens")
        var totalTokens: Int?
    ) : Parcelable
}
