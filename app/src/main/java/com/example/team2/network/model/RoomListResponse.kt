package com.example.team2.network.model

import com.example.team2.presentation.roomlist.model.Room
import com.google.gson.annotations.SerializedName

data class RoomListResponse(
    @SerializedName("message") val message: String,
    @SerializedName("groups") val roomList: List<Room>
)