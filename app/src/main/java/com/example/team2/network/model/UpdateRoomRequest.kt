package com.example.team2.network.model

import com.google.gson.annotations.SerializedName

data class UpdateRoomRequest(
    @SerializedName("groupId") val roomId: String,
    @SerializedName("status") val status: Int
)
