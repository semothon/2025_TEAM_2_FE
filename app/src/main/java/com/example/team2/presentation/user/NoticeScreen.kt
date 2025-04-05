package com.example.team2.presentation.user

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.team2.ui.theme.Brown2

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
            title = "딜리버리를 슬기롭게 이용하는 방법",
            date = "25/03/22",
            createdDate = "2025-03-22",
            createdTime = "10:00",
            content = "이곳에는 공지사항을 자유롭게 적을 거예요. 서로 본론 결론 멋있는 말을 잔뜩 쓰기."
        ),
        NoticeItem("딜리버리 정기 업데이트 공지", "25/03/22", "2025-03-22", "09:00", "딜리버리 정기 업데이트가 3월 23일 실시됩니다."),
        NoticeItem("공지 제목 3", "25/03/22", "2025-03-22", "08:30", "내용 3입니다."),
        NoticeItem("공지 제목 4", "25/03/22", "2025-03-22", "07:45", "내용 4입니다.")
    )

    var selectedNotice by remember { mutableStateOf<NoticeItem?>(null) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "공지사항",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 17.sp
                    )
                },
                navigationIcon = {
                    if (selectedNotice != null) {
                        IconButton(onClick = { selectedNotice = null }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "뒤로가기"
                            )
                        }
                    } else {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "뒤로가기"
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White
                )
            )
        },
        containerColor = Color(0xFFF9F9F9) // 연회색 배경
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            if (selectedNotice == null) {
                NoticeListView(noticeList) { selectedNotice = it }
            } else {
                NoticeDetailView(notice = selectedNotice!!)
            }
        }
    }
}

@Composable
fun NoticeListView(notices: List<NoticeItem>, onItemClick: (NoticeItem) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        Text(
            text = "목록",
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
            fontWeight = FontWeight.Bold,
            color = Brown2
        )
        Spacer(modifier = Modifier.height(12.dp))

        notices.forEach { notice ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .clickable { onItemClick(notice) },
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = notice.date,
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontSize = 10.sp
                            ),
                            color = Brown2.copy(alpha = 0.6f)
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = notice.title,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontSize = 15.sp,
                                fontWeight = FontWeight.SemiBold
                            ),
                            color = Brown2
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f)) // 남은 공간 모두 차지해서 아이콘 밀어냄

                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = null
                    )
                }

            }
        }
    }
}

@Composable
fun NoticeDetailView(notice: NoticeItem) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 20.dp)
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = notice.title,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "운영자   ${notice.date}",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Gray
                )
                Divider(
                    modifier = Modifier.padding(vertical = 12.dp),
                    color = Color(0xFFE0E0E0)
                )
                Text(
                    text = notice.content,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun NoticeScreenPreview() {
    val navController = rememberNavController()
    NoticeScreen(navController = navController)
}
