package com.example.team2.presentation.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.team2.R
import com.example.team2.ui.theme.Brown1
import com.example.team2.ui.theme.Brown2
import com.example.team2.ui.theme.Gray3
import com.example.team2.ui.theme.Gray4
import com.example.team2.ui.theme.MainColor

@Composable
fun SignUpProfileScreen(viewModel: SignUpViewModel) {
    var nickName by remember { mutableStateOf("") }

    Column {
        Box(Modifier.fillMaxWidth()) {
            Spacer(Modifier.height(40.dp))
            Image(
                painter = painterResource(R.drawable.ic_launcher_background),
                contentDescription = "프로필 일러스트",
                modifier = Modifier
                    .align(Alignment.Center)
                    .clip(RoundedCornerShape(100))
            )
        }

        Spacer(Modifier.height(20.dp))
        Text(
            text = "닉네임",
            style = TextStyle(color = Brown2)
        )

        Spacer(Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = nickName,
                onValueChange = { if (it.length <= 20) nickName = it },
                modifier = Modifier.weight(0.6f),
                placeholder = {
                    Text(
                        text = "닉네임을 입력하세요.",
                        style = TextStyle(color = Gray3)
                    )
                },
                shape = RoundedCornerShape(8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Brown1.copy(alpha = 0.2f),
                    unfocusedBorderColor = Brown1.copy(alpha = 0.2f),
                ),
                maxLines = 1
            )

            Spacer(Modifier.width(8.dp))
            Button(
                onClick = { viewModel.duplicationCheck(nickName) },
                modifier = Modifier
                    .weight(0.4f)
                    .height(55.dp)
                    .background(
                        if (nickName.isNotEmpty()) MainColor else Gray4.copy(alpha = 0.2f),
                        RoundedCornerShape(8.dp)
                    ),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                enabled = nickName.isNotEmpty()
            ) {
                Text(
                    text = "중복 확인",
                    style = TextStyle(color = Brown2.copy(alpha = 0.5f))
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpProfilePreview() {
    SignUpProfileScreen(SignUpViewModel())
}