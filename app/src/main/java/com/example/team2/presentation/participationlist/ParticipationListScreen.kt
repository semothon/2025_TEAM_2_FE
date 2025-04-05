package com.example.team2.presentation.participationlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.team2.navigation.home.HomeNavigationItem
import com.example.team2.navigation.participation.ParticipationNavigationItem
import com.example.team2.presentation.component.CustomText2
import com.example.team2.presentation.component.TopBar
import com.example.team2.ui.theme.InnerPadding
import com.example.team2.ui.theme.MainBackground
import com.example.team2.ui.theme.MainColor
import com.example.team2.ui.theme.MainWhite

@Composable
fun ParticipationListScreen(
    navController: NavController,
    viewModel: ParticipationListViewModel = viewModel()
) {
    val isLoading by viewModel.isLoading.collectAsState()
    val roomDeals = viewModel.roomDeals.collectAsState()
    var isDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) { viewModel.getRoomDeals() }

    if (isLoading)
        Scaffold(
            topBar = { TopBar("거래내역") { navController.popBackStack() } },
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .background(MainBackground)
                    .padding(it)
                    .padding(InnerPadding)
                    .padding(bottom = 80.dp)
            ) {
                Row {
                    filterOptions.forEach { option ->
                        Button(
                            onClick = {
                                selectedFilter = if (selectedFilter == option) "" else option
                            },
                            modifier = Modifier
                                .background(
                                    color = if (selectedFilter == option) MainColor else MainWhite
                                ),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                            contentPadding = PaddingValues(0.dp)
                        ) { CustomText2(option) }
                        Spacer(modifier = Modifier.width(4.dp))
                    }
                }

                LazyColumn {
                    items(roomDeals.value) { deal ->
                        ParticipationItem(
                            deal = deal,
                            onClick = {
                                navController.navigate(
                                    ParticipationNavigationItem.ParticipationDetail.destination +
                                            "/${deal.roomId}/${deal.restaurantName}/${deal.roomContent}" ///${deal.tagChips}/${deal.roomStatus}"
                                )
                            },
                            onClickRoomFinish = { isDialog = true }
                        )
                    }
                }

                if (isDialog) {
                    AlertDialog(
                        onDismissRequest = { isDialog = false },
                        confirmButton = {},
                        dismissButton = {},
                        text = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight()
                            ) {
                                // 🔽 텍스트 위아래 패딩 ↓ ↓ ↓ 줄임
                                Text(
                                    text = "거래를 완료하시겠습니까?",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color(0xFF574C4D),
                                    modifier = Modifier
                                        .padding(
                                            top = 4.dp,
                                            bottom = 2.dp
                                        ) // 기존 18 → 12, 16 → 10 정도로 축소
                                        .align(Alignment.CenterHorizontally)
                                )

                                HorizontalDivider(thickness = 1.dp, color = Color(0xFFE0E0E0))

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(48.dp),  // 버튼 영역 높이는 유지
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    TextButton(
                                        onClick = { isDialog = false },
                                        modifier = Modifier
                                            .weight(1f)
                                            .fillMaxHeight()
                                    ) {
                                        Text(
                                            "취소",
                                            color = Color(0xFF574C4D),
                                            fontWeight = FontWeight.Medium
                                        )
                                    }

                                    Box(
                                        modifier = Modifier
                                            .width(1.dp)
                                            .fillMaxHeight()
                                            .background(Color(0xFFE0E0E0))
                                    )

                                    TextButton(
                                        onClick = {
                                            isDialog = false
                                            // 완료 API
                                        },
                                        modifier = Modifier
                                            .weight(1f)
                                            .fillMaxHeight()
                                    ) {
                                        Text(
                                            "완료",
                                            color = Color(0xFFFFCC01),
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                            }
                        },
                        containerColor = Color.White,
                        shape = RoundedCornerShape(16.dp)
                    )
                }
            }
        }
    else
        CircularProgressIndicator()
}

@Preview(showBackground = true)
@Composable
fun ParticipationListPreview() {
    ParticipationListScreen(rememberNavController())
}