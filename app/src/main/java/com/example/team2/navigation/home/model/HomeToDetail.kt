package com.example.team2.navigation.home.model

data class HomeToDetail(
    val roomId: String,
    val roomName: String,
    val roomContent: String,
    val roomTagChips: String?,
    val roomStatus: Int?
)