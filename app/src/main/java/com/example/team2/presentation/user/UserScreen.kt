package com.example.team2.presentation.user

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.team2.R
import com.example.team2.R.drawable.alarmsetting_logo
import com.example.team2.R.drawable.ic_bell
import com.example.team2.R.drawable.inquiry_logo
import com.example.team2.R.drawable.notification_logo
import com.example.team2.R.drawable.policy_logo
import com.example.team2.presentation.user.model.ProfileInfo
import com.example.team2.ui.theme.Blue1
import com.example.team2.ui.theme.Brown2
import com.example.team2.ui.theme.MainWhite

@Composable
fun UserScreen(
    navController: NavController,
    viewModel: EditProfileViewModel
) {
    val profileInfo by viewModel.profileInfo.collectAsState()

    Column(
        Modifier.background(MainWhite)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.width(24.dp)) // 좌측 공간 (뒤로가기 없음)
            Text(
                text = "마이페이지",
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 17.sp),
                color = Brown2
            )
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "알림",
                tint = Color.Gray
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF7F7F7))
                .padding(horizontal = 12.dp)
        ) {
            // 상단 바
            Spacer(modifier = Modifier.height(24.dp))

            // 프로필 타이틀
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate("edit_profile_screen")
                    }
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "프로필",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                )

                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "프로필 수정으로 이동",
                    tint = Color.Gray,
                    modifier = Modifier.size(28.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // 프로필 카드
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .background(Color.White, shape = RoundedCornerShape(16.dp))
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile_illustration_1),
                    contentDescription = "프로필",
                    modifier = Modifier
                        .size(68.18.dp)
                        .clip(CircleShape)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = profileInfo.nickname,
                            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold, fontSize = 16.sp),
                            color = Brown2
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Box(
                            modifier = Modifier
                                .background(Blue1, shape = CircleShape) // 밝은 파란색 배경
                                .padding(horizontal = 5.dp, vertical = 2.dp) // 뱃지 여백 조정
                        ) {
                            Text(
                                text = profileInfo.gender,
                                fontSize = 10.sp,
                                color = MainWhite
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(1.dp))
                    Text(
                        text = "${profileInfo.major} ${profileInfo.year}",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = 13.sp
                        ),
                        color = Brown2
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.heart_icon),
                        contentDescription = "설명",
                        modifier = Modifier
                            .size(20.dp) // 원하는 크기
                            .clip(RoundedCornerShape(8.dp)), // 필요 시 모양 조정
                    )

                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "33개",
                        fontSize = 13.sp,
                        color = Brown2
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 기타 메뉴 리스트
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "기타",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Brown2.copy(alpha = 0.8f)
                )
                MenuItem("알림설정", alarmsetting_logo) {
                    navController.navigate("notification_setting")
                }
                MenuItem("약관 및 정책", policy_logo) {
                    navController.navigate("policy")
                }
                MenuItem("공지사항", notification_logo) {
                    navController.navigate("notice")
                }
                MenuItem("고객 문의", inquiry_logo) {
                    navController.navigate("inquiry")
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            LogoutAndDeleteButtons(
                onLogoutClick = { /* 로그아웃 처리 */ },
                onDeleteClick = { navController.navigate("deleteAccount") }
            )
        }
    }
}

@Composable
fun MenuItem(title: String, iconRes: Int, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
            .padding(vertical = 14.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = title,
                tint = Brown2,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                title,
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 15.sp, fontWeight = FontWeight.SemiBold),
                color = Brown2
            )
        }
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = "이동",
            tint = Brown2
        )
    }
}

@Composable
fun LogoutAndDeleteButtons(
    onLogoutClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 16.dp, bottom = 70.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            TextButton(onClick = onLogoutClick) {
                Text("로그아웃")
            }
            TextButton(onClick = onDeleteClick) {
                Text("회원탈퇴")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserScreenPreview() {
    val fakeNavController = rememberNavController()

    val fakeViewModel = EditProfileViewModel().apply {
        updateProfile(
            ProfileInfo(
                nickname = "꾸벅이",
                name = "이름 1",
                school = "경희대학교",
                major = "기계공학과",
                year = "2024204883",
                gender = "남",
                address = "서울시 송파구"
            )
        )
    }

    UserScreen(
        navController = fakeNavController,
        viewModel = fakeViewModel
    )
}
