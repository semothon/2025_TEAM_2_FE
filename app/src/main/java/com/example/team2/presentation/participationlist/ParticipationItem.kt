package com.example.team2.presentation.participationlist

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.team2.presentation.component.CustomText
import com.example.team2.presentation.component.CustomText3
import com.example.team2.presentation.component.CustomText7
import com.example.team2.presentation.component.IllustrationGrid
import com.example.team2.presentation.participationlist.model.ParticipationRoom
import com.example.team2.ui.theme.Gray3
import com.example.team2.ui.theme.MainColor
import com.example.team2.ui.theme.MainWhite

@Composable
fun ParticipationItem(
    deal: ParticipationRoom,
    isMyRoom: Boolean,
    onClick: () -> Unit,
    onClickRoomFinish: (String) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                1.dp,
                if (deal.roomStatus == 0 || deal.roomStatus == 1) MainColor else MainWhite,
                RoundedCornerShape(15.dp)
            )
            .clickable(
                onClick = { onClick() },
                interactionSource = null,
                indication = null
            ),
        color = Color.White,
        shape = RoundedCornerShape(15.dp),
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                IllustrationGrid(deal.illustrations)

                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    if (deal.roomStatus == 2) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            CustomText3(deal.restaurantName, 0.6f)
                            Spacer(Modifier.width(4.dp))
                            CustomText7("완료", Gray3)
                        }
                        Spacer(Modifier.height(4.dp))
                        CustomText(deal.roomContent, 0.6f)
                    } else {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            CustomText3(deal.restaurantName)
                            Spacer(Modifier.width(4.dp))
                            CustomText7("진행 중", MainColor)
                        }
                        Spacer(Modifier.height(4.dp))
                        CustomText(deal.roomContent, 0.8f)
                    }
                }
            }

            if ((deal.roomStatus == 0 || deal.roomStatus == 1) && isMyRoom)
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = { onClickRoomFinish(deal.roomId) },
                        modifier = Modifier
                            .background(
                                color = MainColor,
                                shape = RoundedCornerShape(97.dp)
                            )
                            .width(150.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        CustomText("완료하기")
                    }
                }
        }
    }
}