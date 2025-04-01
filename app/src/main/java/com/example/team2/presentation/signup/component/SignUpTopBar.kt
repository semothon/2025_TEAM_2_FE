package com.example.team2.presentation.signup.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.team2.ui.theme.Gray2
import com.example.team2.ui.theme.MainColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpTopBar(progress: Int, onClick: () -> Unit) {
    TopAppBar(
        title = {
            Column {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "회원가입",
                        modifier = Modifier.align(Alignment.Center)
                    )
                    IconButton(
                        onClick = { onClick() },
                        modifier = Modifier.align(Alignment.CenterStart)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "뒤로 가기"
                        )
                    }
                }
                LinearProgressIndicator(
                    progress = { progress.toFloat() / 3 },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 16.dp)
                        .height(4.dp)
                        .clip(RoundedCornerShape(63.dp)),
                    color = MainColor,
                    trackColor = Gray2,
                )
            }
        }
    )
}