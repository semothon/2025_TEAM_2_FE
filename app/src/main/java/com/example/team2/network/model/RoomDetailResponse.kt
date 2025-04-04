package com.example.team2.network.model

import com.example.team2.presentation.roomdetail.model.RoomResponseDetail
import com.google.gson.annotations.SerializedName

data class RoomDetailResponse(
    @SerializedName("message") val message: String,
    @SerializedName("group") val roomList: List<RoomResponseDetail>
)