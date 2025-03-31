package com.example.team2.presentation.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.team2.presentation.signup.component.SignUpRowTextAndButton
import com.example.team2.ui.theme.Brown2
import com.example.team2.ui.theme.MainWhite

@Composable
fun SignUpProfileScreen(viewModel: SignUpViewModel) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val randomProfileIllustration by viewModel.randomProfileIllustration.collectAsState()
    var nickName by remember { mutableStateOf("") }

    LaunchedEffect(Unit) { viewModel.buttonEnableFalse() }

    Column {
        Spacer(Modifier.height(40.dp))
        Box(Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.align(Alignment.Center)) {
                Image(
                    painter = painterResource(randomProfileIllustration),
                    contentDescription = "프로필 일러스트",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .clip(RoundedCornerShape(100))
                )
                FloatingActionButton(
                    onClick = { viewModel.refreshRandomProfileIllustration() },
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .size(24.dp),
                    containerColor = Brown2,
                    shape = CircleShape
                ) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "랜덤 프로필 일러스트",
                        modifier = Modifier.size(16.dp),
                        tint = MainWhite
                    )
                }
            }
        }

        Spacer(Modifier.height(8.dp))
        SignUpRowTextAndButton(
            label = "닉네임",
            placeHolder = "닉네임을 입력하세요.",
            buttonText = "중복 확인",
            value = nickName,
            onValueChange = { nickName = it },
            onClick = {
                viewModel.duplicationCheck(nickName)
                keyboardController?.hide()
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpProfilePreview() {
    SignUpProfileScreen(SignUpViewModel())
}