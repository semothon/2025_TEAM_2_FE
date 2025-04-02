package com.example.team2.navigation.home

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.team2.navigation.NavigationViewModel
import com.example.team2.presentation.roomadd.RoomAddScreen
import com.example.team2.presentation.roomdetail.RoomDetailScreen
import com.example.team2.presentation.roomlist.RoomListScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RoomListNavigationGraph(viewModel: NavigationViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = HomeNavigationItem.RoomList.destination
    ) {
        composable(HomeNavigationItem.RoomList.destination) {
            RoomListScreen(navController)
            viewModel.bottomEnableTrue()
        }
        composable(HomeNavigationItem.RoomAdd.destination) {
            RoomAddScreen(navController)
            viewModel.bottomEnableFalse()
        }
        composable(HomeNavigationItem.RoomDetail.destination) {
            RoomDetailScreen(navController)
            viewModel.bottomEnableFalse()
        }
    }
}