package com.example.team2.presentation.roomlist.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.team2.presentation.component.CustomText2
import com.example.team2.ui.theme.MainColor
import com.example.team2.ui.theme.MainWhite

@Composable
fun TagChip(keyword: String) {
    Surface(
        modifier = Modifier.border(1.dp, MainColor, RoundedCornerShape(97.dp)),
        shape = RoundedCornerShape(97.dp),
        color = MainWhite
    ) {
        Box(modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp)) {
            CustomText2(keyword)
        }
    }
}