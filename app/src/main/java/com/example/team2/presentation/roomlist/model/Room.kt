package com.example.team2.presentation.roomlist.model

data class Room(
    val name: String,
    val description: String,
    val keywords: List<String>,
    val participants: Int
)
