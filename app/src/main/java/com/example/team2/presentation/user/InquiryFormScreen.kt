package com.example.team2.presentation.user

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InquiryFormScreen(navController: NavController) {
    var inquiryText by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("문의하기") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "뒤로가기")
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 24.dp)
            ) {
                Text("문의 내용을 입력해주세요:")
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    value = inquiryText,
                    onValueChange = { inquiryText = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    placeholder = { Text("내용을 입력하세요...") }
                )
            }

            // 문의 보내기 버튼을 화면 높이의 약 85% 위치에 배치
            Button(
                onClick = {
                    // TODO: 문의 전송 처리
                    navController.popBackStack()
                },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 80.dp) // 👈 하단에서 살짝 위로 띄움 (조절 가능)
                    .fillMaxWidth(0.9f)
            ) {
                Text("문의 보내기")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InquiryFormScreenPreview() {
    val navController = rememberNavController()
    InquiryFormScreen(navController = navController)
}
