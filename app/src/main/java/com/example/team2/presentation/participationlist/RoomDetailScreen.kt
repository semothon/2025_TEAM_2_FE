package com.example.yourapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    var liked by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) } // 팝업 상태 추가


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 프로필 이미지
        Image(
            painter = painterResource(id = R.drawable.profile_illustration_1),
            contentDescription = "프로필 이미지",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color.LightGray)
                .clickable { showDialog = true }
        )

        Spacer(modifier = Modifier.width(12.dp))

        // 이름 + 학과 + 열쇠 아이콘
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                color = Brown2
            )
            Spacer(modifier = Modifier.width(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = if (name == "낑깡") "$department me" else department,
                    style = MaterialTheme.typography.bodySmall,
                    color = Brown2
                )
                if (isLeader) {
                    Spacer(modifier = Modifier.width(4.dp))
                    Image(
                        painter = painterResource(id = R.drawable.key), // 열쇠 이미지 파일명
                        contentDescription = "방장",
                        modifier = Modifier.size(14.dp)
                    )
                }
            }
        }

        // 좋아요 아이콘 + 텍스트
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            IconButton(
                onClick = { liked = !liked }
            ) {
                Image(
                    painter = painterResource(
                        id = if (liked)
                            R.drawable.group_1321317140_1 // 채워진 하트
                        else
                            R.drawable.group_1321317140    // 빈 하트
                    ),
                    contentDescription = "좋아요",
                    modifier = Modifier.size(24.dp)
                )
            }
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
            KeywordChip("따로 먹을래요", Color(0xFFFFCC01))
            KeywordChip("패스트푸드", Color(0xFFFFCC01))
            KeywordChip("4인팟", Color(0xFFFFCC01))
        }
    }
}

@Composable
fun KeywordChip(text: String, backgroundColor: Color) {
    Row(
        modifier = Modifier
            .background(backgroundColor, RoundedCornerShape(20.dp))
            .padding(horizontal = 12.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        when (text) {
            "따로 먹을래요" -> {
                Image(
                    painter = painterResource(id = R.drawable.ic_separate_eating),
                    contentDescription = "따로 먹을래요 아이콘",
                    modifier = Modifier
                        .size(14.dp)
                        .padding(end = 4.dp)
                )
            }
            "패스트푸드" -> {
                Image(
                    painter = painterResource(id = R.drawable.ic_fastfood),
                    contentDescription = "패스트푸드 아이콘",
                    modifier = Modifier
                        .size(14.dp)
                        .padding(end = 4.dp)
                )
            }
        }

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
                    modifier = Modifier.size(24.dp)
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



@Composable
fun UserProfileDialog(
    onDismiss: () -> Unit,
    userName: String,
    department: String,
    studentId: String,
    likeCount: Int,
    profileImageRes: Int
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {},
        dismissButton = {},
        title = null,
        text = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(top = 8.dp)
            ) {
                // 프로필 이미지
                Image(
                    painter = painterResource(id = profileImageRes),
                    contentDescription = "프로필",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFFFE28C))
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(userName, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.Black)
                Text(department, fontSize = 14.sp, color = Color.Gray)
                Text(studentId, fontSize = 13.sp, color = Color.Gray)

                Spacer(modifier = Modifier.height(12.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.group_1321317140_1), // 하트 이미지
                        contentDescription = "좋아요",
                        tint = Color.Unspecified,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("${likeCount}개", fontSize = 14.sp, color = Color.DarkGray)
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = { /* 차단 기능 */ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFE0E0E0),
                            contentColor = Color.Black
                        )
                    ) {
                        Text("차단하기")
                    }

                    Button(
                        onClick = { /* 채팅 기능 */ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFFCC01),
                            contentColor = Color.Black
                        )
                    ) {
                        Text("채팅 걸기")
                    }
                }
            }
        },
        containerColor = Color.White,
        shape = RoundedCornerShape(20.dp)
    )
}

@Preview
@Composable
fun RoomDetailPreview() {
    RoomDetailScreen()
}
