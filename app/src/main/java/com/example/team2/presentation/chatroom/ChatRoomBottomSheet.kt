package com.example.team2.presentation.chatroom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ChatRoomBottomSheetContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {
        Text(
            text = "채팅방 멤버",
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = Color(0xFF574C4D)
        )

        Spacer(modifier = Modifier.height(12.dp))

        // 멤버 리스트 예시
        val members = listOf(
            Triple("APPLE", "시각디자인학과", true),
            Triple("도라에몽", "컴퓨터공학과", false),
            Triple("크리넥스", "산업경영공학과", false),
            Triple("낑깡", "한국어학과 me", false)
        )

        members.forEach { (name, dept, isLeader) ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(Color.LightGray, shape = CircleShape)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color(0xFF574C4D)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = dept,
                    fontSize = 13.sp,
                    color = Color(0xFF574C4D)
                )
                if (isLeader) {
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("🗝", fontSize = 12.sp)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "방 상세 설명",
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = Color(0xFF574C4D)
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "정건 맥날 같이 시켜드실 분 구합니당.",
            fontSize = 13.sp,
            color = Color(0xFF574C4D)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "방 키워드",
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = Color(0xFF574C4D)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            KeywordChip("따로 먹을래요")
            KeywordChip("패스트푸드")
            KeywordChip("4인팟")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "사진 및 동영상",
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = Color(0xFF574C4D)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            repeat(4) {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .background(Color.LightGray, RoundedCornerShape(8.dp))
                )
            }
        }
    }
}

@Composable
fun KeywordChip(text: String) {
    Box(
        modifier = Modifier
            .background(Color(0xFFFFCC01), RoundedCornerShape(20.dp))
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = text,
            fontSize = 12.sp,
            color = Color(0xFF574C4D)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ChatRoomBottomSheetPreview() {
    ChatRoomBottomSheetContent()
}
