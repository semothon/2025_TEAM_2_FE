package com.example.team2.presentation.roomlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.team2.presentation.component.CustomText
import com.example.team2.presentation.component.CustomText2
import com.example.team2.presentation.component.CustomText3
import com.example.team2.presentation.component.IllustrationGrid
import com.example.team2.presentation.roomlist.component.TagChip
import com.example.team2.presentation.roomlist.model.Room
import com.example.team2.ui.theme.Brown2
import com.example.team2.ui.theme.MainColor
import java.text.DecimalFormat

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
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.Top) {
                IllustrationGrid(room.illustrations)

                Spacer(modifier = Modifier.width(16.dp))
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .height(62.dp)
                        .weight(1f)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        CustomText3(room.restaurantName)
                        Spacer(modifier = Modifier.width(4.dp))
                        val (color, alpha) = if (room.userCost >= room.totalCost) MainColor to 1f else Brown2 to 0.6f
                        val userCost = DecimalFormat("#,###").format(room.userCost)
                        val totalCost = DecimalFormat("#,###").format(room.totalCost)
                        CustomText2("$userCost 원 / $totalCost 원", color, alpha)
                    }
                    Spacer(Modifier.height(4.dp))
                    CustomText(room.content, 0.8f)
                }

                CustomText3("${room.illustrations.size}/${room.totalPeople}", 0.6f)
            }

            Spacer(modifier = Modifier.height(8.dp))
            LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                items(room.tagChips.drop(2)) { keyword ->
                    TagChip(keyword)
                }
            }
        }
    }
}