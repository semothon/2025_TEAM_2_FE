package com.example.team2.presentation.chatroom

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.team2.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun ChatRoomBottomSheetContent() {
    var showExitDialog by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text("채팅방 멤버", fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(12.dp))

            val members = listOf(
                Triple("APPLE", "시각디자인학과", true),
                Triple("도라에몽", "컴퓨터공학과", false),
                Triple("크리넥스", "산업경영공학과", false),
                Triple("낑깡", "한국어학과 me", false)
            )

            members.forEach { (name, dept, isLeader) ->
                Row(
                    modifier = Modifier.padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(Color.LightGray)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = name,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF574C4D)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = dept, fontSize = 13.sp, color = Color.Gray)
                    if (isLeader) {
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("🔑")
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
            Divider(color = Color(0xFFE0E0E0))
            Spacer(modifier = Modifier.height(12.dp))

            Text("방 상세 설명", fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text("정건 맥날 같이 시켜드실 분 구합니다.", fontSize = 13.sp)

            Spacer(modifier = Modifier.height(20.dp))
            Divider(color = Color(0xFFE0E0E0))
            Spacer(modifier = Modifier.height(12.dp))

            Text("방 키워드", fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                KeywordChip("따로 먹을래요")
                KeywordChip("패스트푸드")
                KeywordChip("4인팟")
            }

            Spacer(modifier = Modifier.height(20.dp))
            Divider(color = Color(0xFFE0E0E0))
            Spacer(modifier = Modifier.height(12.dp))

            Text("사진 및 동영상", fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(12.dp))

            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(4) {
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color.LightGray)
                    )
                }
            }
        }

        // 아이콘 하단 고정
        Row(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            IconButton(onClick = { /* 알림 클릭 */ }) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "알림",
                    tint = Color(0xFF574C4D),
                    modifier = Modifier.size(24.dp)
                )
            }
            IconButton(onClick = { showExitDialog = true }) {
                Icon(
                    imageVector = Icons.Default.ExitToApp,
                    contentDescription = "나가기",
                    tint = Color(0xFF574C4D),
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
    if (showExitDialog) {
        AlertDialog(
            onDismissRequest = { showExitDialog = false },
            confirmButton = {
                TextButton(onClick = {
                    showExitDialog = false
                    // TODO: 실제 나가기 로직 추가 (예: navController?.popBackStack())
                }) {
                    Text("나가기", color = Color(0xFFFFCC01), fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showExitDialog = false }) {
                    Text("취소", color = Color(0xFF574C4D))
                }
            },
            title = {
                Text(
                    text = "방을 나가시겠습니까?",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF574C4D)
                )
            },
            containerColor = Color.White,
            shape = RoundedCornerShape(16.dp)
        )
    }

}

@Composable
fun KeywordChip(text: String) {
    Surface(
        shape = RoundedCornerShape(20.dp),
        color = Color(0xFFFFCC01)
    ) {
        Text(
            text = text,
            fontSize = 12.sp,
            color = Color(0xFF574C4D),
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        )
    }
}



@Preview(showBackground = true)
@Composable
fun ChatRoomBottomSheetPreview() {
    ChatRoomBottomSheetContent()
}
