package com.example.team2.presentation.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun DeleteAccountScreen(
    navController: NavController
) {
    var feedback by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1C1C1C)) // 다크 테마 배경
            .padding(horizontal = 24.dp, vertical = 16.dp),
    ) {
        Text(
            text = "회원탈퇴",
            style = MaterialTheme.typography.titleLarge.copy(
                color = Color.White,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "정말 탈퇴하시겠어요?",
            color = Color.White,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text("! 지금 탈퇴하시면 ~~~~", color = Color.White, fontSize = 14.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Text("! 지금 탈퇴하시면 ~~~~", color = Color.White, fontSize = 14.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Text("! 탈퇴 후에는 ~~~~ 이용할 수 없어요.", color = Color.White, fontSize = 14.sp)

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "(체크박스) 회원 탈퇴 유의사항을 확인하였으며 동의합니다.",
            color = Color.White,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text("떠나시는 이유를 알려주세요.", color = Color.White, fontSize = 14.sp)
        Spacer(modifier = Modifier.height(8.dp))

        // 피드백 입력란
        BasicTextField(
            value = feedback,
            onValueChange = { feedback = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(Color.LightGray, shape = MaterialTheme.shapes.medium)
                .padding(12.dp),
            textStyle = TextStyle(fontSize = 14.sp, color = Color.Black),
            decorationBox = { innerTextField ->
                if (feedback.isEmpty()) {
                    Text(
                        text = "서비스 탈퇴 사유를 알려주세요.\n고객님의 소중한 피드백을 담아 더 나은 서비스로 보답 드리도록 하겠습니다.",
                        color = Color.Gray,
                        fontSize = 13.sp
                    )
                }
                innerTextField()
            }
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Text("회원 탈퇴", fontSize = 16.sp)
        }

        // 하단 탭바 높이만큼 여백 확보
        Spacer(modifier = Modifier.height(64.dp))
    }
}
