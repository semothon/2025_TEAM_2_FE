package com.example.team2.navigation

sealed class RoomListNavigationItem(val destination: String) {
    data object AddRoom : SignNavigationItem("방추가")
}