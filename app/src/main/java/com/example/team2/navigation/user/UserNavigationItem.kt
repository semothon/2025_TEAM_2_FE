package com.example.team2.navigation.user

sealed class UserNavigationItem(val route: String) {
    data object User : UserNavigationItem("user_screen")
    data object EditProfile : UserNavigationItem("edit_profile_screen")
    data object NotificationSetting : UserNavigationItem("notification_setting")
}