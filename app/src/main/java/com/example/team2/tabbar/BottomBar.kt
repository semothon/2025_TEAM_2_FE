package com.example.team2.tabbar

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigation
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.team2.navigation.BottomNavigationItem

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomBar(navController: NavController) {
    var selectedItem by rememberSaveable { mutableStateOf("") }
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val bottomItems = listOf(
        BottomNavigationItem.Home,
        BottomNavigationItem.Send,
        BottomNavigationItem.Check,
        BottomNavigationItem.Person
    )

    LaunchedEffect(currentBackStackEntry) {
        selectedItem = currentBackStackEntry?.destination?.route ?: ""
    }

    BottomNavigation(backgroundColor = Color.Gray) {
        bottomItems.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.destination
                    )
                },
                selected = selectedItem == item.destination,
                selectedContentColor = Color.Blue,
                unselectedContentColor = Color.Gray,
                onClick = {
                    navController.navigate(item.destination) {
                        selectedItem = item.destination
                        popUpTo(bottomItems.first().destination) { inclusive = false }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                modifier = Modifier
                    .padding(4.dp)
                    .background(
                        if (selectedItem == item.destination) Color.Blue
                        else Color.Gray,
                        RoundedCornerShape(16.dp)
                    )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    BottomBar(rememberNavController())
}