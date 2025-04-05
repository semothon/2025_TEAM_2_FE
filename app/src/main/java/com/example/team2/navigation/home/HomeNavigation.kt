package com.example.team2.navigation.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.team2.navigation.NavigationViewModel
import com.example.team2.navigation.home.model.HomeToDetail
import com.example.team2.presentation.roomadd.RoomAddScreen
import com.example.team2.presentation.roomdetail.RoomDetailScreen
import com.example.team2.presentation.roomdetail.RoomDetailViewModel
import com.example.team2.presentation.roomlist.RoomListScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeNavigationGraph(viewModel: NavigationViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = HomeNavigationItem.RoomList.destination
    ) {
        composable(HomeNavigationItem.RoomList.destination) {
            viewModel.bottomEnableTrue()
            RoomListScreen(navController)
        }
        composable(HomeNavigationItem.RoomAdd.destination) {
            viewModel.bottomEnableFalse()
            RoomAddScreen(navController)
        }
        composable(
            route = HomeNavigationItem.RoomDetail.destination
                    + "/{roomId}/{roomName}/{roomContent}/{roomTagChips}/{roomStatus}"
        ) { backStackEntry ->
            val roomId = backStackEntry.arguments?.getString("roomId").toString()
            val roomName = backStackEntry.arguments?.getString("roomName").toString()
            val roomContent = backStackEntry.arguments?.getString("roomContent").toString()
            val roomTagChips = backStackEntry.arguments?.getString("roomTagChips").toString()
            val roomStatus = backStackEntry.arguments?.getString("roomStatus").toString()
            val roomDetail =
                HomeToDetail(roomId, roomName, roomContent, roomTagChips, roomStatus)

            viewModel.bottomEnableFalse()
            RoomDetailScreen(navController, roomDetail)
        }
    }
}
