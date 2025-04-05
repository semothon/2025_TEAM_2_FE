package com.example.team2.navigation.participation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.team2.navigation.NavigationViewModel
import com.example.team2.navigation.home.model.HomeToDetail
import com.example.team2.presentation.participationlist.ParticipationListScreen
import com.example.team2.presentation.participationdetail.ParticipationDetailScreen

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
        composable(
            route = ParticipationNavigationItem.ParticipationDetail.destination +
                    "/{roomId}/{roomName}/{roomContent}", ///{roomStatus}" //{roomTagChips}/
            arguments = listOf(
                navArgument("roomId") { type = NavType.StringType },
                navArgument("roomName") { type = NavType.StringType },
                navArgument("roomContent") { type = NavType.StringType },
//                navArgument("roomStatus") { type = NavType.StringType },
//                navArgument("roomTagChips") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val roomId = backStackEntry.arguments?.getString("roomId").toString()
            val roomName = backStackEntry.arguments?.getString("roomName").toString()
            val roomContent = backStackEntry.arguments?.getString("roomContent").toString()
//            val roomTagChips = backStackEntry.arguments?.getString("roomTagChips").toString()
//            val roomStatus = backStackEntry.arguments?.getString("roomStatus").toString()
            val roomDetail = HomeToDetail(roomId, roomName, roomContent) //, roomStatus)

            viewModel.bottomEnableFalse()
            ParticipationDetailScreen(navController, roomDetail)
        }
    }
}