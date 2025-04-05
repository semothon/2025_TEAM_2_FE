package com.example.team2.network.model

import com.example.team2.presentation.roomdetail.model.RoomDetailList
import com.google.gson.annotations.SerializedName

data class RoomResponseDetail(
    @SerializedName("message") val title: String,
    @SerializedName("group") val memberGroup: RoomDetailList
)