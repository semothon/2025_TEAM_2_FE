package com.example.team2.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.team2.ui.theme.Brown1
import com.example.team2.ui.theme.Brown2
import com.example.team2.ui.theme.Gray3
import com.example.team2.ui.theme.Gray4
import com.example.team2.ui.theme.MainWhite

@Composable
fun RowTextAndIcon(
    keyboardController: SoftwareKeyboardController?,
    text: String,
    icon: ImageVector,
    onClickExpanded: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MainWhite, RoundedCornerShape(8.dp))
            .border(1.dp, Brown1.copy(alpha = 0.2f), RoundedCornerShape(8.dp))
            .padding(16.dp)
            .clickable {
                keyboardController?.hide()
                onClickExpanded()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = text,
            style = when (text) {
                "학교를 선택하세요." -> TextStyle(color = Gray4)
                "학과를 선택하세요." -> TextStyle(color = Gray4)
                "입학연도를 선택하세요." -> TextStyle(color = Gray4)
                "카테고리를 선택하세요." -> TextStyle(color = Gray4)
                "희망인원을 선택하세요." -> TextStyle(color = Gray4)
                else -> TextStyle(color = Brown2)
            }
        )
        Icon(
            imageVector = icon,
            contentDescription = "Search",
            tint = Gray3
        )
    }
}