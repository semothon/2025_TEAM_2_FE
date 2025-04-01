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
    val majorOptions = viewModel.majorOptions.collectAsState()
    val yearOptions = viewModel.yearOptions.collectAsState()
    val addressOptions = viewModel.addressOptions.collectAsState()

    var nickname by rememberSaveable { mutableStateOf("") }
    var major by rememberSaveable { mutableStateOf("학과 선택") }
    var year by rememberSaveable { mutableStateOf("입학년도 선택") }
    var gender by rememberSaveable { mutableStateOf("") }
    var address by rememberSaveable { mutableStateOf("주소 선택") }

    var majorExpanded by remember { mutableStateOf(false) }
    var yearExpanded by remember { mutableStateOf(false) }
    var addressExpanded by remember { mutableStateOf(false) }

    // 데이터 자동 저장
    LaunchedEffect(nickname, major, year, gender, address) {
        viewModel.saveProfile(ProfileInfo(nickname, major, year, gender, address))
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("프로필 수정", style = MaterialTheme.typography.titleLarge)

        Spacer(Modifier.height(20.dp))
        Text("닉네임")
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = nickname,
            onValueChange = { nickname = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("닉네임을 입력하세요.") },
            shape = RoundedCornerShape(8.dp)
        )

        Spacer(Modifier.height(20.dp))
        Text("학과")
        Spacer(Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Gray4, RoundedCornerShape(8.dp))
                .padding(16.dp)
                .clickable { majorExpanded = true }
        ) {
            Text(text = major, color = if (major == "학과 선택") Gray4 else Color.Black)
        }
        DropdownMenu(expanded = majorExpanded, onDismissRequest = { majorExpanded = false }) {
            majorOptions.value.forEach {
                DropdownMenuItem(
                    text = { Text(it) },
                    onClick = {
                        major = it
                        majorExpanded = false
                    }
                )
            }
        }

        Spacer(Modifier.height(20.dp))
        Text("입학년도")
        Spacer(Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Gray4, RoundedCornerShape(8.dp))
                .padding(16.dp)
                .clickable { yearExpanded = true }
        ) {
            Text(text = year, color = if (year == "입학년도 선택") Gray4 else Color.Black)
        }
        DropdownMenu(expanded = yearExpanded, onDismissRequest = { yearExpanded = false }) {
            yearOptions.value.forEach {
                DropdownMenuItem(
                    text = { Text(it) },
                    onClick = {
                        year = it
                        yearExpanded = false
                    }
                )
            }
        }

        Spacer(Modifier.height(20.dp))
        Text("성별")
        Spacer(Modifier.height(8.dp))
        Row {
            listOf("남성", "여성").forEach { option ->
                Button(
                    onClick = { gender = option },
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp)
                        .border(
                            if (gender == option) 1.dp else 0.dp,
                            if (gender == option) MainColor else Color.Transparent,
                            RoundedCornerShape(8.dp)
                        )
                        .background(
                            if (gender == option) MainWhite else Gray1,
                            RoundedCornerShape(8.dp)
                        ),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(
                        text = option,
                        color = if (gender == option) MainColor else Gray5
                    )
                }
            }
        }

        Spacer(Modifier.height(20.dp))
        Text("주소")
        Spacer(Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Gray4, RoundedCornerShape(8.dp))
                .padding(16.dp)
                .clickable { addressExpanded = true }
        ) {
            Text(text = address, color = if (address == "주소 선택") Gray4 else Color.Black)
        }
        DropdownMenu(expanded = addressExpanded, onDismissRequest = { addressExpanded = false }) {
            addressOptions.value.forEach {
                DropdownMenuItem(
                    text = { Text(it) },
                    onClick = {
                        address = it
                        addressExpanded = false
                    }
                )
            }
        }
        Button(onClick = {
            navController.navigate("user_screen") {
                popUpTo("user_screen") { inclusive = true } // 스택에서 제거하고 이동
            }
        }) {
            Text("완료", fontWeight = FontWeight.Bold)
        }

    }
}
