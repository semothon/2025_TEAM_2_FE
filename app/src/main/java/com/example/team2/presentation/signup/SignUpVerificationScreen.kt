package com.example.team2.presentation.signup

import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.team2.presentation.component.SignUpCustomText
import com.example.team2.presentation.signup.model.SignUp
import com.example.team2.ui.theme.Brown1
import com.example.team2.ui.theme.Brown2
import com.example.team2.ui.theme.Gray3
import com.example.team2.ui.theme.Gray4
import com.example.team2.ui.theme.MainColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun SignUpVerificationScreen(viewModel: SignUpViewModel) {
    val context = LocalContext.current
    val signUpList = viewModel.signUpVerificationList.collectAsState()
    var email by rememberSaveable { mutableStateOf("") }
    var verificationCode by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }

    Column {
        signUpList.value.forEach { value ->
            SignUpVerificationTextField(
                label = value.label,
                placeHolder = value.textPlaceholder,
                buttonText = value.buttonText,
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
                        "학교 이메일" -> {}
                        "인증 코드" -> {}
                        "비밀번호" -> {}
                        "비밀번호 확인" -> {
                            if (password == confirmPassword)
                                CoroutineScope(Dispatchers.IO).launch {
                                    viewModel.verification(
                                        SignUp(
                                            email,
                                            verificationCode,
                                            password,
                                            confirmPassword
                                        )
                                    )
                                }
                            else
                                Toast.makeText(context, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT)
                                    .show()
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun SignUpVerificationTextField(
    label: String,
    placeHolder: String,
    buttonText: String,
    value: String,
    onValueChange: (String) -> Unit,
    onClick: (String) -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Spacer(Modifier.height(20.dp))
        SignUpCustomText(label)

        Spacer(Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier.weight(0.6f),
                placeholder = {
                    Text(
                        text = placeHolder,
                        style = TextStyle(color = Gray3)
                    )
                },
                visualTransformation =
                if (label == "비밀번호" || label == "비밀번호 확인")
                    PasswordVisualTransformation()
                else
                    VisualTransformation.None,
                shape = RoundedCornerShape(8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Brown1.copy(alpha = 0.2f),
                    unfocusedBorderColor = Brown1.copy(alpha = 0.2f),
                ),
                maxLines = 1
            )

            Spacer(Modifier.width(8.dp))
            Button(
                onClick = { onClick(label) },
                modifier = Modifier
                    .weight(0.4f)
                    .height(55.dp)
                    .background(
                        if (value.isNotEmpty()) MainColor else Gray4.copy(alpha = 0.2f),
                        RoundedCornerShape(8.dp)
                    ),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                enabled = value.isNotEmpty()
            ) {
                Text(
                    text = buttonText,
                    style = TextStyle(color = Brown2.copy(alpha = if (value.isNotEmpty()) 1f else 0.5f))
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpVerificationPreview() {
    SignUpVerificationScreen(SignUpViewModel())
}