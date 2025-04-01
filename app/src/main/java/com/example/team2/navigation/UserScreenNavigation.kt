package com.example.team2.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.team2.presentation.user.EditProfileScreen
import com.example.team2.presentation.user.EditProfileViewModel
import com.example.team2.presentation.user.UserScreen

@Composable
fun UserScreenNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = UserScreenNavigationItem.User.route
    ) {
        composable(UserScreenNavigationItem.User.route) {
            UserScreen(navController)
        }
        composable(UserScreenNavigationItem.EditProfile.route) {
            val viewModel: EditProfileViewModel = viewModel()
            EditProfileScreen(viewModel = viewModel, navController = navController)
        }

    }
}
