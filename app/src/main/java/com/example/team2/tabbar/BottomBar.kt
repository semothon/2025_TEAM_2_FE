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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.team2.navigation.BottomNavigationItem

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomBar(navController: NavHostController) {
    var selectedItem by rememberSaveable { mutableStateOf(BottomNavigationItem.Home.title) }
    val bottomItems = listOf(
        BottomNavigationItem.Home,
        BottomNavigationItem.Send,
        BottomNavigationItem.Check,
        BottomNavigationItem.Person
    )

    BottomNavigation(backgroundColor = Color.Gray) {
        bottomItems.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                selected = selectedItem == item.title,
                selectedContentColor = Color.Blue,
                unselectedContentColor = Color.Gray,
                onClick = {
                    selectedItem = item.title
                    navController.navigate(item.title)
                },
                modifier = Modifier
                    .padding(4.dp)
                    .background(
                        if (selectedItem == item.title) Color.Blue
                        else Color.Gray,
                        RoundedCornerShape(16.dp)
                    )
            )
        }
    }
}

@Preview
@Composable
fun BottomNavigationBarPreview() {
    BottomBar(rememberNavController())
}