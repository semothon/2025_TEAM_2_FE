package com.example.team2.presentation.roomadd.model

data class RoomDetail(
    val title: String,
    val content: String,
    val isTogether: Boolean,
    val totalPeople: Int,
    val gender: String,
    val location: String
)