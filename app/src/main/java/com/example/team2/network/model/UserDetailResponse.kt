package com.example.team2.network.model

import com.example.team2.presentation.roomdetail.model.User
import com.google.gson.annotations.SerializedName

data class UserDetailResponse(
    @SerializedName("message") val message:String,
    @SerializedName("user") val userDetailList: User
)