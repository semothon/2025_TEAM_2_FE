package com.example.team2.network.model

import com.google.gson.annotations.SerializedName

data class SendMessageRequest(
    @SerializedName("groupId") val roomId: String,
    @SerializedName("message") val message: String,
)
