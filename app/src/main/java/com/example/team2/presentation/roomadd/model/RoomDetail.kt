package com.example.team2.presentation.roomadd.model

import com.google.gson.annotations.SerializedName

data class RoomDetail(
    @SerializedName("title") val restaurantName: String,
    @SerializedName("note") val content: String,
    @SerializedName("foodCategory") val foodCategory: String,
    @SerializedName("maxPeople") val totalPeople: Int,
    @SerializedName("together") val isTogether: Boolean,
    @SerializedName("sameGender") val gender: Boolean,
    @SerializedName("hashtags") val hashTags: List<Any>,
    @SerializedName("location") val location: String
)