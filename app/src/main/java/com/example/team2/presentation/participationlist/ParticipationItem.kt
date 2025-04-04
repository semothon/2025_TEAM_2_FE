package com.example.team2.presentation.participationlist

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.team2.presentation.component.CustomText
import com.example.team2.presentation.component.CustomText3
import com.example.team2.presentation.component.CustomText7
import com.example.team2.presentation.participationlist.model.ParticipationRoom
import com.example.team2.ui.theme.Gray3
import com.example.team2.ui.theme.MainColor
import com.example.team2.ui.theme.MainWhite

@Composable
fun ParticipationItem(deal: ParticipationRoom, onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, if (deal.roomStatus == "2") MainColor else MainWhite)
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
                    if (deal.roomStatus == "2") {
                        Row {
                            CustomText3(deal.restaurantName, 0.6f)
                            Spacer(Modifier.width(4.dp))
                            CustomText7("완료", Gray3)
                        }
                        Spacer(Modifier.height(4.dp))
                        CustomText(deal.roomContent, 0.6f)
                    } else {
                        Row {
                            CustomText3(deal.restaurantName)
                            Spacer(Modifier.width(4.dp))
                            CustomText7("진행중", MainColor)
                        }
                        Spacer(Modifier.height(4.dp))
                        CustomText(deal.roomContent, 0.8f)
                    }
                }
            }
        }
    }
}