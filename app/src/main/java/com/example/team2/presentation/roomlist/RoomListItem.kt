package com.example.team2.presentation.roomlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.team2.presentation.component.CustomText
import com.example.team2.presentation.component.CustomText3
import com.example.team2.presentation.roomlist.component.TagChip
import com.example.team2.presentation.roomlist.model.Room

@Composable
fun RoomListItem(room: Room, onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                onClick = { onClick() },
                interactionSource = null,
                indication = null
            ),
        color = Color.White,
        shape = RoundedCornerShape(15.dp),
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    modifier = Modifier.size(60.dp),
                    tint = Color.Gray
                )

                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    CustomText3(room.restaurantName)
                    Spacer(Modifier.height(4.dp))
                    CustomText(room.content, 0.8f)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                items(room.tagChips) { keyword ->
                    TagChip(keyword)
                }
            }
        }
    }
}