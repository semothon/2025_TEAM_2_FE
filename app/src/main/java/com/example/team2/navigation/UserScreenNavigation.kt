package com.example.team2.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.team2.presentation.user.EditProfileScreen
import com.example.team2.presentation.user.EditProfileViewModel
import com.example.team2.presentation.user.InquiryScreen
import com.example.team2.presentation.user.NoticeScreen
import com.example.team2.presentation.user.NotificationSettingScreen
import com.example.team2.presentation.user.PolicyScreen
import com.example.team2.presentation.user.UserScreen

@Composable
fun UserScreenNavigation() {
    val navController = rememberNavController()
    val sharedViewModel: EditProfileViewModel = viewModel()

    NavHost(navController = navController, startDestination = "user_screen") {
        composable("user_screen") {
            UserScreen(navController = navController, viewModel = sharedViewModel)
        }
        composable("edit_profile_screen") {
            EditProfileScreen(navController = navController, viewModel = sharedViewModel)
        }
        composable("notification_setting") {
            NotificationSettingScreen(navController) // 🔸 알림설정 화면 추가
        }
        composable("policy") {
            PolicyScreen(navController)
        }
        composable("notice") {
            NoticeScreen()
        }
        composable("inquiry") {
            InquiryScreen()
        }
    }
}
