package com.example.team2.presentation.signup

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import com.example.team2.presentation.signup.component.SignUpRowTextAndButton

@Composable
fun SignUpVerificationScreen(viewModel: SignUpViewModel) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val signUpList = viewModel.signUpVerificationList.collectAsState()
    var email by rememberSaveable { mutableStateOf("") }
    var verificationCode by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }

    Column {
        signUpList.value.forEach { value ->
            SignUpRowTextAndButton(
                label = value.label,
                placeHolder = value.textPlaceholder,
                buttonText = value.buttonText,
                isButton = value.label != "비밀번호",
                value = when (value.label) {
                    "학교 이메일" -> email
                    "인증 코드" -> verificationCode
                    "비밀번호" -> password
                    "비밀번호 확인" -> confirmPassword
                    else -> ""
                },
                onValueChange = {
                    when (value.label) {
                        "학교 이메일" -> email = it
                        "인증 코드" -> verificationCode = it
                        "비밀번호" -> password = it
                        "비밀번호 확인" -> confirmPassword = it
                    }
                },
                onClick = {
                    when (value.label) {
                        "학교 이메일" -> viewModel.univVerifyCodeRequest(email)
                        "인증 코드" -> viewModel.univVerifyCodeCertRequest(email, verificationCode)
                        "비밀번호" -> {}
                        "비밀번호 확인" ->
                            if (password == confirmPassword) {
                                keyboardController?.hide()
                                viewModel.savePassword(password)
                            } else
                                Toast.makeText(context, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT)
                                    .show()

                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpVerificationPreview() {
    SignUpVerificationScreen(SignUpViewModel())
}