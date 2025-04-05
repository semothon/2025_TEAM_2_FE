package com.example.team2.presentation.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.team2.ui.theme.Brown2
import com.example.team2.ui.theme.MainWhite



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FaqScreenWrapper(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "자주 묻는 질문(FAQ)",
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
                            tint = Brown2
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MainWhite
                )
            )
        },
        containerColor = Color(0xFFF9F9F9)
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            FaqScreen(navController)
        }
    }
}

@Composable
fun FaqScreen(navController: NavController) {
    val faqList = listOf(
        "학교 인증은 어떻게 하나요?" to "회원가입 시 최초 학교인증을 진행하고 있습니다.",
        "채팅 알림이 안 와요." to "설정 > 알림설정에서 채팅 알림을 켰는지 확인해보세요.",
        "탈퇴는 어떻게 하나요?" to "마이페이지 > 설정 > 회원 탈퇴에서 가능합니다."
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MainWhite)
            .padding(horizontal = 20.dp, vertical = 20.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        faqList.forEachIndexed { index, (question, answer) ->
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                // "자주 묻는 질문 N"
                Text(
                    text = "자주 묻는 질문 ${index + 1}",
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = Brown2.copy(alpha = 0.6f)
                )

                // Q/A 카드
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = MainWhite),
                    elevation = CardDefaults.cardElevation(1.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(0.95f)
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
fun FaqScreenPreview() {
    val navController = rememberNavController()
    FaqScreen(navController = navController)
}
