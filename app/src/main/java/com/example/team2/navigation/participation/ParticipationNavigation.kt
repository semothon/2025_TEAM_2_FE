package com.example.team2.navigation.participation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.team2.navigation.NavigationViewModel
import com.example.team2.presentation.participationlist.ParticipationListScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ParticipationNavigationGraph(viewModel: NavigationViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ParticipationNavigationItem.ParticipationList.destination
    ) {
        composable(ParticipationNavigationItem.ParticipationList.destination) {
            viewModel.bottomEnableTrue()
            ParticipationListScreen(navController)
        }
        composable(ParticipationNavigationItem.ParticipationDetail.destination) {
            viewModel.bottomEnableFalse()

        }
    }
}