package com.example.team2.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.team2.presentation.participationlist.ParticipationListScreen
import com.example.yourapp.ui.screen.RoomDetailScreen

@Composable
fun ParticipationNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "room_detail"
    ) {
        composable("room_detail") {
            RoomDetailScreen(navController)
        }
        composable("participation_list") {
            ParticipationListScreen()
        }
    }
}
