package com.example.team2.navigation

sealed class RoomListNavigationItem(val destination: String) {
    data object RoomAdd : SignNavigationItem("방추가")
}