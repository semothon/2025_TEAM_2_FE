package com.example.team2.navigation

sealed class ParticipationNavigationItem(val route: String) {
    object ParticipationList : ParticipationNavigationItem("participation_list")
    object RoomDetail : ParticipationNavigationItem("room_detail/{roomId}") {
        fun createRoute(roomId: String): String = "room_detail/$roomId"
    }
}
