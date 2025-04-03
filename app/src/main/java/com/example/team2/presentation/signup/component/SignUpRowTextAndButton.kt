package com.example.team2.presentation.signup.component

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.team2.presentation.component.CustomText
import com.example.team2.ui.theme.Brown1
import com.example.team2.ui.theme.Brown2
import com.example.team2.ui.theme.Gray2
import com.example.team2.ui.theme.Gray3
import com.example.team2.ui.theme.Gray4
import com.example.team2.ui.theme.MainColor

@Composable
fun SignUpRowTextAndButton(
    label: String,
    placeHolder: String,
    buttonText: String,
    value: String,
    onValueChange: (String) -> Unit,
    onClick: (String) -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Spacer(Modifier.height(20.dp))
        CustomText(label)

        Spacer(Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                textStyle = TextStyle(color = Brown2),
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
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    disabledContainerColor = Gray4.copy(alpha = 0.2f)
                ),
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
