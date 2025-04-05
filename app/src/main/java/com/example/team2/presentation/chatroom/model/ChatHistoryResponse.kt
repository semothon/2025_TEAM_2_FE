package com.example.team2.presentation.chatroom.model

import com.google.gson.annotations.SerializedName

data class ChatHistoryDetail(
    @SerializedName("messageId") val messageId: String,
    @SerializedName("senderInfo") val senderInfo: SenderInfo,
    @SerializedName("message") val message: String,
    @SerializedName("timestamp") val timestamp: String,
)

data class SenderInfo(
    @SerializedName("userId") val userId: String,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("icon") val illustration: Int,
)