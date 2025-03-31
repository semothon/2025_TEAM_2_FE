package com.example.team2.network.model

import com.google.gson.annotations.SerializedName

data class VerifyCodeCertRequest(
    @SerializedName("email") val email: String,
    @SerializedName("code") val code: String
)