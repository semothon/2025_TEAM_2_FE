package com.example.team2.presentation.user

import android.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.team2.R
import com.example.team2.presentation.user.model.ProfileInfo


@Composable
fun UserScreen(
    navController: NavController,
    viewModel: EditProfileViewModel
) {
    val profileInfo by viewModel.profileInfo.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .padding(bottom = 80.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // 상단 제목
        Text("마이페이지", style = MaterialTheme.typography.titleLarge)


        // 🔻 중앙 정렬되는 프로필 영역
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val image = painterResource(R.drawable.profile_illustration_1)
            Box {
                Image(
                    painter = image,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    alpha = 0.5F,
                    modifier = Modifier.size(120.dp)
                )
            }

            Spacer(Modifier.height(5.dp))

            Text(profileInfo.nickname, style = MaterialTheme.typography.titleMedium)
            Text("pordoneo@khu.ac.kr")
            Text("${profileInfo.major} / ${profileInfo.year} / ${profileInfo.gender}")
            Text(profileInfo.address)

            Spacer(Modifier.height(4.dp))

            Button(onClick = {
                navController.navigate("edit_profile_screen")
            }) {
                Text("프로필 수정")
            }
        }

        // 🔻 나머지 영역은 기존처럼 좌측 정렬

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("받은 좋아요 수")
            Text("152")
        }

        Spacer(Modifier.height(16.dp))

        MenuItem("알림설정") {
            navController.navigate("notification_setting")
        }
        MenuItem("약관 및 정책") {
            navController.navigate("policy")
        }
        MenuItem("공지사항") {
            navController.navigate("notice")
        }
        MenuItem("고객 문의") {
            navController.navigate("inquiry")
        }
        LogoutAndDeleteButtons(
            onLogoutClick = { /* 로그아웃 처리 */ },
            onDeleteClick = { navController.navigate("deleteAccount") }
        )
    }

}

@Composable
fun MenuItem(title: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .clickable(onClick = onClick),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(title)
        Text(">")
    }
}

@Composable
fun LogoutAndDeleteButtons(
    onLogoutClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize() // 화면 전체 크기
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart) // 좌측 하단 정렬
                .padding(start = 0.dp, bottom = 70.dp), // 여백 제거
            verticalArrangement = Arrangement.spacedBy(0.dp) // 버튼 사이 여백 없애기 (원하면 조정 가능)
        ) {
            TextButton(
                onClick = onLogoutClick,
                contentPadding = PaddingValues(0.dp) // 버튼 안쪽 여백 제거 (선택)
            ) {
                Text("로그아웃")
            }
            TextButton(
                onClick = onDeleteClick,
                contentPadding = PaddingValues(0.dp)
            ) {
                Text("회원탈퇴")
            }
        }
    }
}





@Preview(showBackground = true)
@Composable
fun UserScreenPreview() {
    val fakeNavController = rememberNavController()

    // 가짜 ViewModel 인스턴스 생성 + 더미 데이터 주입
    val fakeViewModel = EditProfileViewModel().apply {
        updateProfile(
            ProfileInfo(
                nickname = "프리뷰 닉네임",
                major = "프리뷰학과",
                year = "22학번",
                gender = "여성",
                address = "프리뷰 주소"
            )
        )
    }

    UserScreen(
        navController = fakeNavController,
        viewModel = fakeViewModel
    )
}
