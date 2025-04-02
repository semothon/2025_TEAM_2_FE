package com.example.team2.presentation.user

import androidx.compose.foundation.clickable
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
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // 상단 제목
        Text("마이페이지", style = MaterialTheme.typography.titleLarge)

        Spacer(Modifier.height(16.dp))

        // 프로필 정보 표시 (원래는 이미지도 있지만 지금은 생략)
        Text(profileInfo.nickname, style = MaterialTheme.typography.titleMedium)
        Text("pordoneo@khu.ac.kr") // 이메일 고정값
        Text("${profileInfo.major} / ${profileInfo.year} / ${profileInfo.gender}")
        Text(profileInfo.address)

        // 수정 버튼
        Button(
            onClick = {
                navController.navigate("edit_profile_screen")
            },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("프로필 수정")
        }

        Spacer(Modifier.height(16.dp))

        // 좋아요 수
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("받은 좋아요 수")
            Text("152")
        }

        Spacer(Modifier.height(16.dp))

        // 설정 메뉴 항목
        MenuItem("알림설정") {
            navController.navigate("notification_setting")
        }
        MenuItem("약관 및 정책") {
            navController.navigate("policy")
        }
        MenuItem("공지사항") {
            navController.navigate("notice")
        }
        MenuItem("고객 문의") {
            navController.navigate("inquiry")
        }

    }
}

@Composable
fun MenuItem(title: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .clickable(onClick = onClick),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(title)
        Text(">")
    }
}

