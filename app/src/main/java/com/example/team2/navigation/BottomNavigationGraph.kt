package com.example.team2.navigation

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.team2.navigation.home.HomeNavigationGraph
import com.example.team2.navigation.user.UserScreenNavigation
import com.example.team2.presentation.chatlist.ChatListScreen
import com.example.team2.presentation.participationlist.ParticipationListScreen
import com.example.team2.presentation.tabbar.BottomBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomNavigationGraph(viewModel: NavigationViewModel = viewModel()) {
    val navController = rememberNavController()
    val isButton by viewModel.isBottom.collectAsState()

    Scaffold(
        bottomBar = { if (isButton) BottomBar(navController) }
    ) {
        NavHost(
            navController = navController,
            startDestination = BottomNavigationItem.Home.destination
        ) {
            composable(BottomNavigationItem.Home.destination) {
                HomeNavigationGraph(viewModel)
            }
            composable(BottomNavigationItem.Send.destination) {
                ChatListScreen()
            }
            composable(BottomNavigationItem.Check.destination) {
                ParticipationListScreen()
            }
            composable(BottomNavigationItem.Person.destination) {
                UserScreenNavigation()
            }
        }
    }
}