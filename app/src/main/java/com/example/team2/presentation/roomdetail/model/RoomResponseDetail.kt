package com.example.team2.presentation.roomdetail.model

import com.google.gson.annotations.SerializedName

data class RoomResponseDetail(
    @SerializedName("message") val title: String,
    @SerializedName("group") val memberGroup: RoomDetailList
)

data class RoomDetailList(
    @SerializedName("location") val location: String,
    @SerializedName("membersInfo") val members: List<Member>
)