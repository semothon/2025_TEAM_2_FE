package com.example.team2.network.model

import com.google.gson.annotations.SerializedName

data class SignInResponse(
    @SerializedName("message") val message: String,
    @SerializedName("token") val token: String,
    @SerializedName("userId") val userId: String
)