package com.example.team2.presentation.roomdetail.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("userId") val userId: String,
    @SerializedName("icon") val illustration: Int,
    @SerializedName("nickname") val nickName: String,
    @SerializedName("username") val userName: String,
    @SerializedName("school") val univInfo: List<Any>,
    @SerializedName("gender") val gender: String,
    @SerializedName("likeCount") val favoriteCount: Int,
)