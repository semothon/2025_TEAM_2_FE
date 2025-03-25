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
fun NavigationGraph() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomBar(navController) }
    ) {
        NavHost(
            navController = navController,
            startDestination = BottomNavigationItem.Home.title
        ) {
            composable(BottomNavigationItem.Home.title) { RoomListScreen() }
            composable(BottomNavigationItem.Send.title) { ChatListScreen() }
            composable(BottomNavigationItem.Check.title) { ParticipationListScreen() }
            composable(BottomNavigationItem.Person.title) { UserScreen() }
        }
    }
}