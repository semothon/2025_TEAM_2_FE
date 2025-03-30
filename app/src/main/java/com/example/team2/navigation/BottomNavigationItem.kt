package com.example.team2.navigation

import com.example.team2.R

sealed class BottomNavigationItem(
    val destination: String, val icon: Int
) {
    data object Home : BottomNavigationItem("홈", R.drawable.home_icon)
    data object Send : BottomNavigationItem("채팅", R.drawable.chat_icon)
    data object Check : BottomNavigationItem("거래내역", R.drawable.transaction_icon)
    data object Person : BottomNavigationItem("마이페이지", R.drawable.user_icon)
}