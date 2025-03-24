package com.example.team2.navigation

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.team2.roomlist.RoomListScreen
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
            composable(BottomNavigationItem.Send.title) { /* 목록 화면 */ }
            composable(BottomNavigationItem.Check.title) { /* 설정 화면 */ }
            composable(BottomNavigationItem.Person.title) { /* 설정 화면 */ }
        }
    }
}