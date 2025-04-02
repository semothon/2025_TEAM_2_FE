package com.example.team2.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.team2.presentation.roomadd.RoomAddScreen

@Composable
fun RoomListNavigationGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = RoomListNavigationItem.RoomAdd.destination
    ) {
        composable(RoomListNavigationItem.RoomAdd.destination) {
            RoomAddScreen(navController)
        }
    }
}