package com.example.team2.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.example.team2.ui.theme.MainWhite

@Composable
fun DropDownMenu(
    options: List<String>,
    isExpanded: MutableState<Boolean>,
    onClickExpanded: () -> Unit,
    onClickOption: (String) -> Unit
) {
    DropdownMenu(
        expanded = isExpanded.value,
        onDismissRequest = { onClickExpanded() },
        modifier = Modifier
            .shadow(8.dp)
            .background(MainWhite),
    ) {
        Column(
            modifier = Modifier
                .heightIn(max = 200.dp)
                .verticalScroll(rememberScrollState())
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        onClickOption(option)
                        onClickExpanded()
                    },
                    text = { Text(text = option) },
                )
            }
        }
    }
}