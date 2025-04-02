package com.example.team2.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.team2.ui.theme.Gray2
import com.example.team2.ui.theme.MainColor

@Composable
fun BottomButton(text: String, isButton: Boolean, onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(color = if (isButton) MainColor else Gray2),
        enabled = isButton,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            disabledContainerColor = Gray2
        ),
        contentPadding = PaddingValues(0.dp),
        shape = RoundedCornerShape(0)
    ) {
        CustomText4(text, if (isButton) 1f else 0.7f)
    }
}