package com.example.team2.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.team2.ui.theme.Brown1
import com.example.team2.ui.theme.Brown2
import com.example.team2.ui.theme.Gray4
import com.example.team2.ui.theme.MainWhite

@Composable
fun CustomOutlinedTextField(
    value: String,
    placeholder: String,
    maxLines: Int = 1,
    isNumber: Boolean = false,
    onValueChanged: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = {
            if (it.length <= 100) {
                onValueChanged(it)
            }
        },
        textStyle = TextStyle(color = Brown2),
        modifier = Modifier
            .fillMaxWidth()
            .height(30.dp + 30.dp * maxLines)
            .background(MainWhite, RoundedCornerShape(8.dp)),
        placeholder = {
            Text(
                text = placeholder,
                style = TextStyle(color = Gray4)
            )
        },
        shape = RoundedCornerShape(8.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Brown1.copy(alpha = 0.2f),
            unfocusedBorderColor = Brown1.copy(alpha = 0.2f),
        ),
        maxLines = maxLines,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = if (isNumber) KeyboardType.Number else KeyboardType.Text
        )
    )
}