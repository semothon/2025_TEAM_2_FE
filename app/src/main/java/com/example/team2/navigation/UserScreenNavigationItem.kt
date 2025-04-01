package com.example.team2.navigation

sealed class UserScreenNavigationItem(val route: String) {
    object User : UserScreenNavigationItem("user_screen")
    object EditProfile : UserScreenNavigationItem("edit_profile_screen")
}