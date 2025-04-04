package com.example.team2.model

data class Transaction(
    val id: Int,
    val roomName: String,
    val roomDesc: String,
    val isOngoing: Boolean,
    val participants: List<Int>
)
