package com.example.team2.presentation.roomadd.model

data class AddRoom(
    val title: String,
    val content: String,
    val isTogether: Boolean,
    val totalPeople: Int,
    val genderPreference: String,
    val location: String
)