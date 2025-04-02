package com.example.team2.presentation.user

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun UserScreen(
    navController: NavController,
    viewModel: EditProfileViewModel
) {
    val profileInfo by viewModel.profileInfo.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("마이페이지", style = MaterialTheme.typography.titleLarge)

        Spacer(Modifier.height(12.dp))

        Text("닉네임: ${profileInfo.nickname}")
        Text("학과: ${profileInfo.major}")
        Text("입학년도: ${profileInfo.year}")
        Text("성별: ${profileInfo.gender}")
        Text("주소: ${profileInfo.address}")

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = {
                navController.navigate("edit_profile_screen")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("프로필 수정")
        }
    }
}
