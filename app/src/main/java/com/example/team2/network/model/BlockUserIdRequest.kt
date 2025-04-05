package com.example.team2.network.model

import com.google.gson.annotations.SerializedName

data class BlockUserIdRequest(
    @SerializedName("targetUserId") val userId: String
)