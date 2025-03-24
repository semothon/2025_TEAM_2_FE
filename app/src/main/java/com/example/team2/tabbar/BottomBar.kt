package com.example.team2.tabbar

import android.annotation.SuppressLint
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigation
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.team2.navigation.BottomNavigationItem

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomBar(navController: NavHostController) {
    val bottomItems = listOf(
        BottomNavigationItem.Home,
        BottomNavigationItem.Send,
        BottomNavigationItem.Check,
        BottomNavigationItem.Person
    )
    BottomNavigation {
        bottomItems.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                selected = false,
                onClick = { navController.navigate(item.title) }
            )
        }
    }
}

@Preview
@Composable
fun BottomNavigationBarPreview() {
    BottomBar(rememberNavController())
}