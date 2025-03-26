package com.example.team2.navigation

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.team2.presentation.chatlist.ChatListScreen
import com.example.team2.presentation.participationlist.ParticipationListScreen
import com.example.team2.presentation.roomlist.RoomListScreen
import com.example.team2.presentation.user.UserScreen
import com.example.team2.tabbar.BottomBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomNavigationGraph() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomBar(navController) }
    ) {
        NavHost(
            navController = navController,
            startDestination = BottomNavigationItem.Home.destination
        ) {
            composable(BottomNavigationItem.Home.destination) { RoomListScreen() }
            composable(BottomNavigationItem.Send.destination) { ChatListScreen() }
            composable(BottomNavigationItem.Check.destination) { ParticipationListScreen() }
            composable(BottomNavigationItem.Person.destination) { UserScreen() }
        }
    }
}