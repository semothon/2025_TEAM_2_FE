package com.example.team2.presentation.signup.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.team2.ui.theme.Gray1
import com.example.team2.ui.theme.Gray5
import com.example.team2.ui.theme.MainColor
import com.example.team2.ui.theme.MainWhite

@Composable
fun SignUpGenderButton(gender: String, value: String, modifier: Modifier, onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        modifier = modifier
            .height(50.dp)
            .border(
                if (value == gender) 1.dp else 0.dp,
                if (value == gender) MainColor else Color.Transparent,
                RoundedCornerShape(8.dp)
            )
            .background(
                if (value == gender) MainWhite else Gray1,
                RoundedCornerShape(8.dp)
            ),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(
            text = value,
            style = TextStyle(color = if (value == gender) MainColor else Gray5)
        )
    }
}