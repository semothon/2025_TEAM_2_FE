package com.example.team2.presentation.user

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FaqScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("자주 묻는 질문 (FAQ)") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "뒤로가기")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Q. 회원탈퇴는 어떻게 하나요?")
            Text("A. 마이페이지 > 회원탈퇴 버튼을 누르면 탈퇴가 가능합니다.")
            Spacer(Modifier.height(16.dp))
            Text("Q. 채팅 알림이 안 와요.")
            Text("A. 설정 > 알림설정에서 채팅 알림을 켰는지 확인해보세요.")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FaqScreenPreview() {
    val navController = rememberNavController()
    FaqScreen(navController = navController)
}
