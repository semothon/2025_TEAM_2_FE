package com.example.team2.presentation.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.team2.ui.theme.Brown2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InquiryHistoryScreen(navController: NavController) {
    val inquiryList = listOf(
        "25/03/23" to ("학교 인증은 어떻게 하나요?" to "회원가입 시 최초 학교인증을 진행하고 있습니다."),
        "25/03/23" to ("결제 취소는 어떻게 하나요?" to "마이페이지 > 결제 내역에서 취소 가능합니다."),
        "25/03/23" to ("채팅이 자꾸 끊겨요." to "인터넷 연결 상태를 확인해주세요.")
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "문의 내역",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontSize = 17.sp,
                            fontWeight = FontWeight.SemiBold
                        ),
                        color = Brown2
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "뒤로가기",
                            tint = Color.Black
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White
                )
            )
        },
        containerColor = Color(0xFFF9F9F9)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 20.dp, vertical = 20.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            inquiryList.forEach { (date, qa) ->
                val (question, answer) = qa

                // 날짜 텍스트
                Text(
                    text = date,
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = Brown2.copy(alpha = 0.5f)
                )

                // Q&A 카드
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp), // ← 여기로 카드 간 거리 조절
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "Q. $question",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Medium
                            ),
                            color = Brown2
                        )
                        Text(
                            text = "A. $answer",
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontSize = 15.sp
                            ),
                            color = Brown2
                        )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun InquiryHistoryScreenPreview() {
    val navController = rememberNavController()
    InquiryHistoryScreen(navController = navController)
}