package com.example.team2.navigation.home

sealed class HomeNavigationItem(val destination: String) {
    data object RoomList : HomeNavigationItem("방리스트")
    data object RoomAdd : HomeNavigationItem("방추가")
    data object RoomDetail : HomeNavigationItem("방정보")
}