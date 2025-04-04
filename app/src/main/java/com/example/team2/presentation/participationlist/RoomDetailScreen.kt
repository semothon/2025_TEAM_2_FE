package com.example.yourapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.team2.R
import com.example.team2.presentation.participationlist.ParticipationListScreen
import com.example.team2.ui.theme.Brown2
import com.example.team2.ui.theme.Gray6
import com.example.team2.ui.theme.MainBackground
import com.example.team2.ui.theme.MainWhite


@Composable
fun RoomDetailScreen(navController: NavController? = null) {
    //뒤로가기 바는 Nav 설정 이후에 구현하기
    // Title
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MainWhite)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            // 1. 가운데 텍스트
            Text(
                text = "맥도날드",
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black,
                modifier = Modifier.align(Alignment.Center)
            )

            // 2. 왼쪽 아이콘
            IconButton(
                onClick = { /* 뒤로가기 동작 없음 */ },
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "뒤로가기",
                    tint = Color.Black
                )
            }

            // 3. 오른쪽 아이콘
            IconButton(
                onClick = { /* 알림 아이콘 동작 */ },
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "알림",
                    tint = Color.Black
                )
            }
        }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MainBackground)
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
            style = MaterialTheme.typography.titleMedium.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            ),
            color = Brown2
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
            Triple("낑깡", "한국어학과", false)
        )

        members.forEach { (name, dept, isLeader) ->
            MemberItem(name = name, department = dept, isLeader = isLeader)
        }
        Spacer(modifier = Modifier.height(8.dp))
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
        Image(
            painter = painterResource(id = R.drawable.profile_illustration_1),
            //나중에 진짜 프로필 가져오기
            contentDescription = "프로필 이미지",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color.LightGray) // 로딩 중일 경우 대비
        )


        Spacer(modifier = Modifier.width(12.dp))

        Row(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                color = Brown2
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = department,
                style = MaterialTheme.typography.bodySmall,
                color = Brown2
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.FavoriteBorder,
                contentDescription = "좋아요",
                tint = Brown2,
                modifier = Modifier.size(25.dp) // 필요 시 크기 조정
            )
            Text(
                text = "좋아요",
                fontSize = 12.sp,
                color = Brown2
            )
        }

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
            style = MaterialTheme.typography.titleMedium.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            ),
            color = Brown2
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
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            color = Brown2
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "정건 맥날 같이 시켜드실 분 구합니당.",
            style = MaterialTheme.typography.bodyMedium,
            color = Brown2
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "방 키워드",
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            color = Brown2
        )

        Spacer(modifier = Modifier.height(12.dp))

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
            style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp),
            color = Brown2
        )
    }
}

@Composable
fun RoomStatusSection() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "방 상태",
            style = MaterialTheme.typography.titleMedium,
            color = Brown2
        )

        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(12.dp))
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Row(
                modifier = Modifier.align(Alignment.CenterStart),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "모집 완료",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Brown2
                )

                Spacer(modifier = Modifier.width(250.dp))

                IconButton(
                    onClick = { /* TODO: 드롭다운 동작 등 */ },
                    modifier = Modifier.size(24.dp) // 아이콘 사이즈 조절
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "메뉴 열기",
                        tint = Gray6
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun RoomDetailPreview() {
    RoomDetailScreen()
}
