package com.example.team2.presentation.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.example.team2.ui.theme.Brown2

@Composable
fun SignUpCustomText(text: String) {
    Text(
        text = text,
        style = TextStyle(
            color = Brown2,
            fontSize = 12.sp
        )
    )
}