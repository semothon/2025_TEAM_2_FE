package com.example.team2.network.model

import com.example.team2.presentation.chatroom.model.ChatHistoryDetail
import com.google.gson.annotations.SerializedName

data class ChatHistoryResponse(
    @SerializedName("message") val message: String,
    @SerializedName("chats") val chatHistory: List<ChatHistoryDetail>
)