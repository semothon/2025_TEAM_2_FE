package com.example.team2.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavigationItem(
    val title: String, val icon: ImageVector
) {
    data object Home : BottomNavigationItem("홈", Icons.Default.Home)
    data object Send : BottomNavigationItem("채팅", Icons.AutoMirrored.Filled.Send)
    data object Check : BottomNavigationItem("거래내역", Icons.Default.Check)
    data object Person : BottomNavigationItem("마이페이지", Icons.Default.Person)
}