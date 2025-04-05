package com.example.team2.presentation.roomlist.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Room(
    @SerializedName("groupId") val roomId: String,
    @SerializedName("title") val restaurantName: String,
    @SerializedName("note") val content: String,
    @SerializedName("hashtags") val tagChips: List<String>,
    @SerializedName("status") val status: Int,
    @SerializedName("icons") val illustrations: List<Int>,
    @SerializedName("maxPeople") val totalPeople: Int,
    @SerializedName("creatorGender") val gender: String
) : Serializable
