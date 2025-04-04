package com.example.team2.network.model

import com.google.gson.annotations.SerializedName

data class MakeRoomResponse(
    @SerializedName("message") val message: String,
    @SerializedName("groupId") val groupId: String
)