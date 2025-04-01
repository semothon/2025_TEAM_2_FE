package com.example.team2.presentation.user

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.team2.R
import com.example.team2.navigation.UserScreenNavigationItem

@Composable
fun UserScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("마이페이지", style = MaterialTheme.typography.titleLarge)

        Spacer(Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.profile_illustration_1), // 임시 이미지
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(Modifier.width(16.dp))
            Column {
                Text("닉네임2", fontWeight = FontWeight.Bold)
                Text("pordoneo@khu.ac.kr")
                Text("컴퓨터종합과 / 21학번 / 남")
                Text("사색의광장 배달존 A")
            }
        }

        Spacer(Modifier.height(12.dp))

        Button(onClick = {
            navController.navigate(UserScreenNavigationItem.EditProfile.route)
        }) {
            Text("프로필 수정")
        }

        Spacer(Modifier.height(16.dp))

        Text("받은 좋아요 수: 152")

        Spacer(Modifier.height(16.dp))

        MenuItem("알림설정")
        MenuItem("약관 및 정책")
        MenuItem("공지사항")
        MenuItem("고객 문의")

        Spacer(Modifier.weight(1f))

        Text("로그아웃", color = MaterialTheme.colorScheme.error)
        Text("회원탈퇴", color = MaterialTheme.colorScheme.error)
    }
}

@Composable
fun MenuItem(title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .clickable { },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(title)
    }
}

