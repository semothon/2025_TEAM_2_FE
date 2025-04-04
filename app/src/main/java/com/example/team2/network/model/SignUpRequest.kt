package com.example.team2.network.model

import com.google.gson.annotations.SerializedName

data class SignUpRequest(
    @SerializedName("username") val name: String,
    @SerializedName("password") val password: String,
    @SerializedName("studentId") val year: String,
    @SerializedName("major") val department: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("nickname") val nickName: String,
    @SerializedName("icon") val illustration: Int,
    @SerializedName("email") val email: String
)