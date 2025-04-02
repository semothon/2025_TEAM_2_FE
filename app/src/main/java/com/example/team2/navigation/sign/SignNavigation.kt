package com.example.team2.navigation.sign

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.team2.navigation.BottomNavigationGraph
import com.example.team2.presentation.signin.SignInScreen
import com.example.team2.presentation.signup.SignUpScreen

@Composable
fun SignNavigationGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = SignNavigationItem.SignIn.destination
    ) {
        composable(SignNavigationItem.SignIn.destination) {
            SignInScreen(navController)
        }
        composable(SignNavigationItem.SignUp.destination) {
            SignUpScreen(navController)
        }
        composable(SignNavigationItem.BottomNavigationGraph.destination) {
            BottomNavigationGraph()
        }
    }
}