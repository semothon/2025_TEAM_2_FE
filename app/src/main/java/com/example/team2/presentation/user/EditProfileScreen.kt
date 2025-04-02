package com.example.team2.presentation.user

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.team2.presentation.user.model.ProfileInfo
import com.example.team2.ui.theme.Gray1
import com.example.team2.ui.theme.Gray4
import com.example.team2.ui.theme.Gray5
import com.example.team2.ui.theme.MainColor
import com.example.team2.ui.theme.MainWhite


@Composable
fun EditProfileScreen(
    viewModel: EditProfileViewModel,
    navController: NavController
) {
    val profileInfo by viewModel.profileInfo.collectAsState()

    // 🟡 상태 변수 - 초기값은 ViewModel에서 가져오기
    var nickname by rememberSaveable { mutableStateOf(profileInfo.nickname) }
    var major by rememberSaveable { mutableStateOf(profileInfo.major) }
    var year by rememberSaveable { mutableStateOf(profileInfo.year) }
    var gender by rememberSaveable { mutableStateOf(profileInfo.gender) }
    var address by rememberSaveable { mutableStateOf(profileInfo.address) }

    var majorExpanded by remember { mutableStateOf(false) }
    var yearExpanded by remember { mutableStateOf(false) }
    var addressExpanded by remember { mutableStateOf(false) }

    val majorOptions by viewModel.majorOptions.collectAsState()
    val yearOptions by viewModel.yearOptions.collectAsState()
    val addressOptions by viewModel.addressOptions.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        Text("프로필 수정", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(16.dp))

        // 닉네임 입력
        OutlinedTextField(
            value = nickname,
            onValueChange = { nickname = it },
            label = { Text("닉네임") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))

        // 학과 드롭다운
        Box(modifier = Modifier
            .fillMaxWidth()
            .clickable { majorExpanded = true }
            .padding(12.dp)
            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))) {
            Text(text = major)
        }
        DropdownMenu(
            expanded = majorExpanded,
            onDismissRequest = { majorExpanded = false }
        ) {
            majorOptions.forEach {
                DropdownMenuItem(
                    text = { Text(it) },
                    onClick = {
                        major = it
                        majorExpanded = false
                    }
                )
            }
        }

        Spacer(Modifier.height(12.dp))

        // 입학년도 드롭다운
        Box(modifier = Modifier
            .fillMaxWidth()
            .clickable { yearExpanded = true }
            .padding(12.dp)
            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))) {
            Text(text = year)
        }
        DropdownMenu(
            expanded = yearExpanded,
            onDismissRequest = { yearExpanded = false }
        ) {
            yearOptions.forEach {
                DropdownMenuItem(
                    text = { Text(it) },
                    onClick = {
                        year = it
                        yearExpanded = false
                    }
                )
            }
        }

        Spacer(Modifier.height(12.dp))

        // 성별 선택 버튼
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            listOf("남성", "여성").forEach { option ->
                Button(
                    onClick = { gender = option },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (gender == option) Color.Yellow else Color.LightGray
                    )
                ) {
                    Text(option)
                }
            }
        }

        Spacer(Modifier.height(12.dp))

        // 주소 드롭다운
        Box(modifier = Modifier
            .fillMaxWidth()
            .clickable { addressExpanded = true }
            .padding(12.dp)
            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))) {
            Text(text = address)
        }
        DropdownMenu(
            expanded = addressExpanded,
            onDismissRequest = { addressExpanded = false }
        ) {
            addressOptions.forEach {
                DropdownMenuItem(
                    text = { Text(it) },
                    onClick = {
                        address = it
                        addressExpanded = false
                    }
                )
            }
        }

        Spacer(Modifier.height(20.dp))

        // 완료 버튼 → ViewModel에 업데이트 + 뒤로가기
        Button(
            onClick = {
                viewModel.updateProfile(
                    ProfileInfo(nickname, major, year, gender, address)
                )
                navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("완료")
        }
    }
}
