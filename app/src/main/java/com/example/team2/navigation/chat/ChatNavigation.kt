package com.example.team2.navigation.chat

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.team2.navigation.BottomNavigationGraph
import com.example.team2.navigation.NavigationViewModel
import com.example.team2.navigation.participation.ParticipationNavigationItem
import com.example.team2.navigation.sign.SignNavigationItem
import com.example.team2.presentation.chatlist.ChatListScreen
import com.example.team2.presentation.chatroom.ChatRoomScreen
import com.example.team2.presentation.signin.SignInScreen
import com.example.team2.presentation.signup.SignUpScreen

@Composable
fun ChatNavigationGraph(viewModel: NavigationViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ChatNavigationItem.ChatList.destination
    ) {
        composable(ChatNavigationItem.ChatList.destination) {
            viewModel.bottomEnableTrue()
            ChatListScreen(navController)
        }
        composable(
            route = ChatNavigationItem.ChatRoom.destination + "/{roomId}"
        ) { backStackEntry ->
            val roomId = backStackEntry.arguments?.getString("roomId").toString()

            viewModel.bottomEnableFalse()
            ChatRoomScreen(navController, roomId)
        }
    }
}