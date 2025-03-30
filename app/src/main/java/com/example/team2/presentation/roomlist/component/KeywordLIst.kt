package com.example.team2.presentation.roomlist.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.team2.presentation.component.CustomText
import com.example.team2.presentation.component.CustomText2
import com.example.team2.presentation.roomlist.RoomListViewModel
import com.example.team2.ui.theme.MainColor
import com.example.team2.ui.theme.MainWhite

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun KeywordList(
    viewModel: RoomListViewModel,
    initialKeywords: List<String>,
    additionalKeywords: List<String>
) {
    var expanded by remember { mutableStateOf(false) }
    val displayedKeywords = if (expanded) initialKeywords + additionalKeywords else initialKeywords
    val selectedKeywords by viewModel.selectedKeywords.collectAsState()

    Column {
        CustomText(text = "추천 키워드")
        Spacer(Modifier.height(8.dp))

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            displayedKeywords.forEach { keyword ->
                val color =
                    if (selectedKeywords.contains(keyword)) MainColor
                    else MainWhite
                KeywordChip(keyword, color) {
                    viewModel.editKeyword(keyword)
                }
            }

            Surface(
                shape = RoundedCornerShape(16.dp),
                color = Color.White,
                modifier = Modifier.clickable { expanded = !expanded }
            ) {
                Box(modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp)) {
                    CustomText2(text = if (expanded) "간단히 보기" else "더보기")
                }
            }
        }
    }
}