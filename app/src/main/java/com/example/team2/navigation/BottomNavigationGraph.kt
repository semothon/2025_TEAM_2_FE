package com.example.team2.navigation

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.team2.presentation.chatlist.ChatListScreen
import com.example.team2.presentation.participationlist.ParticipationListScreen
import com.example.team2.presentation.roomlist.RoomListScreen
import com.example.team2.presentation.user.UserScreen
import com.example.team2.presentation.tabbar.BottomBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomNavigationGraph() {
    val navController = rememberNavController()
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != RoomListNavigationItem.AddRoom.destination) {
                BottomBar(navController)
            }
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = BottomNavigationItem.Home.destination
        ) {
            composable(BottomNavigationItem.Home.destination) {
                RoomListScreen(navController)
            }
            composable(RoomListNavigationItem.AddRoom.destination) {
                RoomListNavigationGraph()
            }
            composable(BottomNavigationItem.Send.destination) {
                ChatListScreen()
            }
            composable(BottomNavigationItem.Check.destination) {
                ParticipationListScreen()
            }
            composable(BottomNavigationItem.Person.destination) {
                UserScreen()
            }
        }
    }
}