package com.example.team2.presentation.signin.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.team2.ui.theme.Brown2

@Composable
fun SignOptions(
    onIdClick: () -> Unit,
    onPasswordClick: () -> Unit,
    onSignUpClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SignOptionsText("아이디 찾기") { onIdClick() }
        HorizontalDivider(
            modifier = Modifier
                .size(1.dp, 10.dp)
                .background(Brown2)
        )
        SignOptionsText("비밀번호 찾기") { onPasswordClick() }
        HorizontalDivider(
            modifier = Modifier
                .size(1.dp, 10.dp)
                .background(Brown2)
        )
        SignOptionsText("회원가입") { onSignUpClick() }
    }
}

@Composable
fun SignOptionsText(text: String, onClick: () -> Unit) {
    Text(
        text = text,
        modifier = Modifier.clickable { onClick() },
        style = TextStyle(color = Brown2)
    )
}