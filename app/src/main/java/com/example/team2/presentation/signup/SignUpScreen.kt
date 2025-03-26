package com.example.team2.presentation.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SignUpScreen(viewModel: SignUpViewModel = SignUpViewModel()) {
    val form by viewModel.form.collectAsState()
    val isEmailVerified by viewModel.isEmailVerified.collectAsState()
    val schools by viewModel.schools.collectAsState()

    // 이메일 인증 상태 메시지
    val emailVerificationMessage = if (isEmailVerified) {
        "Email verified."
    } else {
        "Code is incorrect."
    }

    Column(modifier = Modifier
        .padding(16.dp)
        .verticalScroll(rememberScrollState())) {
        // 이미지
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color.Gray),
            contentAlignment = Alignment.Center
        ) {
            Text("앱 이미지")
        }

        // 이메일 입력
        OutlinedTextField(
            value = form.email,
            onValueChange = { viewModel.updateForm(form.copy(email = it)) },
            label = { Text("메일 주소") },
            placeholder = { Text("enter your email") },
            isError = !isEmailVerified,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )
        Text(
            text = emailVerificationMessage,
            color = if (isEmailVerified) Color.Green else Color.Red,
            modifier = Modifier.padding(top = 8.dp)
        )

        // 인증 코드 입력
        OutlinedTextField(
            value = form.verificationCode,
            onValueChange = { viewModel.updateForm(form.copy(verificationCode = it)) },
            label = { Text("인증 코드") },
            placeholder = { Text("enter verification code") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )

        // 학교 선택
        DropdownMenu(
            expanded = true, // To always show dropdown menu for the sake of example
            onDismissRequest = { },
        ) {
            schools.forEach { school ->
                DropdownMenuItem(
                    onClick = { viewModel.selectSchool(school) },
                    text = { Text(text = school) }
                )
            }
        }

        // 학교, 학과, 학번, 성별 등 입력 필드
        OutlinedTextField(
            value = form.department,
            onValueChange = { viewModel.updateForm(form.copy(department = it)) },
            label = { Text("학과") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )
        OutlinedTextField(
            value = form.studentId,
            onValueChange = { viewModel.updateForm(form.copy(studentId = it)) },
            label = { Text("학번") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )

        Row(modifier = Modifier.padding(top = 16.dp)) {
            RadioButton(
                selected = form.gender == "남",
                onClick = { viewModel.updateForm(form.copy(gender = "남")) }
            )
            Text("남", modifier = Modifier.padding(start = 8.dp))
            RadioButton(
                selected = form.gender == "여",
                onClick = { viewModel.updateForm(form.copy(gender = "여")) }
            )
            Text("여", modifier = Modifier.padding(start = 8.dp))
        }

        // 닉네임
        OutlinedTextField(
            value = form.nickname,
            onValueChange = { viewModel.updateForm(form.copy(nickname = it)) },
            label = { Text("닉네임") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )

        // 전화번호
        OutlinedTextField(
            value = form.phone,
            onValueChange = { viewModel.updateForm(form.copy(phone = it)) },
            label = { Text("전화번호") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )

        // 비밀번호
        OutlinedTextField(
            value = form.phone,
            onValueChange = { viewModel.updateForm(form.copy(phone = it)) },
            label = { Text("비밀번호") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )

        // 가입하기 버튼
        Button(
            onClick = { /* 회원가입 처리 */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text("가입하기")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpPreview(){
    SignUpScreen()
}