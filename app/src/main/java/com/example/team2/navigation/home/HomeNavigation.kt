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
            route = HomeNavigationItem.RoomDetail.destination +
                    "/{roomId}/{roomName}/{roomContent}/{roomTagChips}"
            // /roomTagChips" },
//            arguments = listOf(navArgument("roomId") { type = NavType.StringType },
//                navArgument("roomName") { type = NavType.StringType },
//                navArgument("roomContent") { type = NavType.StringType },
//                navArgument("roomTagChips") { type = NavType.StringType }
//            ),
        ) { backStackEntry ->
            val roomId = backStackEntry.arguments?.getString("roomId").toString()
            val roomName = backStackEntry.arguments?.getString("roomName").toString()
            val roomContent = backStackEntry.arguments?.getString("roomContent").toString()
            val roomTagChips = backStackEntry.arguments?.getString("roomTagChips")
            val roomDetail = HomeToDetail(roomId, roomName, roomContent, roomTagChips)

            viewModel.bottomEnableFalse()
            RoomDetailScreen(navController, roomDetail)
        }
    }
}
