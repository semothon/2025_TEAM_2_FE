package com.example.team2.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.team2.ui.theme.Brown2
import com.example.team2.ui.theme.MainBlack
import com.example.team2.ui.theme.MainWhite

@Composable
fun SearchBar(
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit
) {
    TextField(
        value = searchQuery,
        onValueChange = onSearchQueryChanged,
        placeholder = {
            Text(
                text = "검색어를 입력하세요",
                style = TextStyle(color = Brown2.copy(alpha = 0.4f))
            )
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "검색 아이콘",
                tint = Color.Gray
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .shadow(24.dp, RoundedCornerShape(8.dp), spotColor = MainBlack),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MainWhite,
            unfocusedContainerColor = MainWhite,
            disabledContainerColor = MainWhite,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(8.dp)
    )
}