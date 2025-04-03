package com.example.team2.presentation.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.team2.ui.theme.Brown2

@Composable
fun CustomText(text: String, alpha: Float = 1f) {
    Text(
        text = text,
        style = TextStyle(
            color = Brown2.copy(alpha = alpha),
            fontSize = 13.sp
        )
    )
}

@Composable
fun CustomText2(text: String) {
    Text(
        text = text,
        style = TextStyle(
            color = Brown2,
            fontSize = 12.sp
        )
    )
}

@Composable
fun CustomText3(text: String) {
    Text(
        text = text,
        style = TextStyle(
            color = Brown2,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
        )
    )
}

@Composable
fun CustomText4(text: String, alpha: Float = 1f) {
    Text(
        text = text,
        style = TextStyle(
            color = Brown2.copy(alpha = alpha),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    )
}