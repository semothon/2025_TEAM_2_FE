package com.example.team2.network.model

import com.google.gson.annotations.SerializedName

data class SearchQuery(
    @SerializedName("groupId") val roomId:String
)
