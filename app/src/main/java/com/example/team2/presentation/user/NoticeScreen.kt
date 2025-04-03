package com.example.team2.presentation.user

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController

data class NoticeItem(
    val title: String,
    val date: String,
    val createdDate: String,
    val createdTime: String,
    val content: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoticeScreen(navController: NavController) {
    val noticeList = listOf(
        NoticeItem(
            title = "서버 점검 안내",
            date = "25/3/25",
            createdDate = "2025-03-25",
            createdTime = "10:00",
            content = "3월 25일 오전 10시부터 1시간 동안 서버 점검이 진행됩니다."
        ),
        NoticeItem(
            title = "신기능 업데이트",
            date = "25/3/22",
            createdDate = "2025-03-22",
            createdTime = "14:30",
            content = "새로운 채팅 기능이 추가되었습니다. 업데이트를 확인해주세요!"
        ),
        NoticeItem(
            title = "약관 개정 안내",
            date = "25/3/10",
            createdDate = "2025-03-10",
            createdTime = "09:15",
            content = "이용약관이 일부 개정되었습니다. 변경된 내용을 확인해 주세요."
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("공지사항") },
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
                .padding(horizontal = 20.dp, vertical = 12.dp)
        ) {
            noticeList.forEach { item ->
                NoticeAccordionItem(item)
            }
        }
    }
}

@Composable
fun NoticeAccordionItem(item: NoticeItem) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded }
            .padding(vertical = 12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(item.title, fontWeight = FontWeight.Medium)
            Text(item.date, style = MaterialTheme.typography.bodySmall)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Icon(
                imageVector = if (expanded) Icons.Filled.KeyboardArrowDown
                else Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null
            )
        }

        if (expanded) {
            Spacer(Modifier.height(8.dp))
            Text("작성일: ${item.createdDate}")
            Text("작성시간: ${item.createdTime}")
            Spacer(Modifier.height(4.dp))
            Text(item.content)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NoticeScreenPreview() {
    val navController = rememberNavController()
    NoticeScreen(navController = navController)
}
