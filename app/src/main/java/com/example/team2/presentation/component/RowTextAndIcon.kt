package com.example.team2.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.team2.ui.theme.Brown2
import com.example.team2.ui.theme.Gray3
import com.example.team2.ui.theme.Gray4
import com.example.team2.ui.theme.MainWhite

@Composable
fun RowTextAndIcon(
    keyboardController: SoftwareKeyboardController?,
    text: String,
    placeholder: String,
    icon: ImageVector,
    onClickExpanded: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MainWhite, RoundedCornerShape(8.dp))
            .clickable(
                interactionSource = null,
                indication = null,
                onClick = {
                    keyboardController?.hide()
                    onClickExpanded()
                }
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = { },
            textStyle = TextStyle(color = Brown2),
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = placeholder,
                    style = TextStyle(color = Gray4)
                )
            },
            trailingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = "Search",
                    tint = Gray3
                )
            },
            enabled = false
        )
    }
}