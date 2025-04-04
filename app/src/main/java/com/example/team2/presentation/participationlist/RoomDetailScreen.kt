package com.example.yourapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun RoomDetailScreen(navController: NavController) {
    //뒤로가기 바는 Nav 설정 이후에 구현하기

    // Title

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7F7))
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFFE07D))
                .padding(12.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "맥도날드",
                style = MaterialTheme.typography.titleLarge
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF7F7F7))
                .padding(horizontal = 16.dp)
        ) {


            Spacer(modifier = Modifier.height(20.dp))
            MemberSection()
            Spacer(modifier = Modifier.height(20.dp))
            RoomDetailSection()
            Spacer(modifier = Modifier.height(20.dp))
            RoomStatusSection()
        }
    }
}

@Composable
fun MemberSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF7F7F7))
            .padding(bottom = 12.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = "인원 현황",
            style = MaterialTheme.typography.titleMedium,
            color = Color(0xFF333333)
        )
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(16.dp))
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        val members = listOf(
            Triple("APPLE", "시각디자인학과", true),
            Triple("도라에몽", "컴퓨터공학과", false),
            Triple("크리넥스", "산업경영공학과", false),
            Triple("낑깡", "한국어학과 me", false)
        )

        members.forEach { (name, dept, isLeader) ->
            MemberItem(name = name, department = dept, isLeader = isLeader)
        }
    }
}

@Composable
fun MemberItem(name: String, department: String, isLeader: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Placeholder profile image
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color(0xFFFFE07D), CircleShape)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = name,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
            )
            Text(
                text = department,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Icon(
            imageVector = Icons.Default.FavoriteBorder,
            contentDescription = "좋아요",
            tint = Color.Gray
        )
    }
}

@Composable
fun RoomDetailSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF7F7F7))
            .padding(bottom = 12.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = "방 상세",
            style = MaterialTheme.typography.titleMedium,
            color = Color(0xFF333333)
        )
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Text(
            text = "방 상세 설명",
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "정건 맥날 같이 시켜드실 분 구합니당.",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "방 키워드",
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            KeywordChip("따로 먹을래요", Color(0xFFFFC94A))
            KeywordChip("패스트푸드", Color(0xFFFFC94A))
            KeywordChip("4인팟", Color(0xFFFFC94A))
        }
    }
}

@Composable
fun KeywordChip(text: String, backgroundColor: Color) {
    Box(
        modifier = Modifier
            .background(backgroundColor, RoundedCornerShape(20.dp))
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp)
        )
    }
}

@Composable
fun RoomStatusSection() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "방 상태",
            style = MaterialTheme.typography.titleMedium,
            color = Color(0xFF333333)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(12.dp))
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Text(
                text = "모집 완료",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
