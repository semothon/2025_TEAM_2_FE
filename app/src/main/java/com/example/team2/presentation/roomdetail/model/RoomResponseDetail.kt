package com.example.team2.presentation.roomdetail.model

import com.google.gson.annotations.SerializedName

data class RoomResponseDetail(
    @SerializedName("location")val title: String,
    @SerializedName("membersInfo")val members:List<Member>
)