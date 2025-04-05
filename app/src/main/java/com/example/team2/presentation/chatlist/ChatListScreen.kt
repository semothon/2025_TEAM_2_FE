package com.example.team2.presentation.chatlist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.team2.navigation.chat.ChatNavigationItem
import com.example.team2.presentation.component.CustomText2
import com.example.team2.presentation.component.TopBar
import com.example.team2.ui.theme.Gray7
import com.example.team2.ui.theme.InnerPadding
import com.example.team2.ui.theme.MainBackground
import com.example.team2.ui.theme.MainColor
import com.example.team2.ui.theme.MainWhite
import com.example.team2.userId
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ChatListScreen(
    navController: NavController,
    viewModel: ChatListViewModel = viewModel()
) {
    val isLoading by viewModel.isLoading.collectAsState()
    val roomDeals by viewModel.filteredRoomDeals.collectAsState()
    var isDialog by remember { mutableStateOf(false) }
    var roomId by remember { mutableStateOf("") }

    LaunchedEffect(Unit) { viewModel.getRoomDeals() }

    Scaffold(
        topBar = { TopBar("채팅", false) {} }
    ) { paddingValues ->
        if (isLoading)
            Column(
                Modifier
                    .fillMaxSize()
                    .background(MainBackground)
                    .padding(paddingValues)
                    .padding(InnerPadding)
                    .padding(bottom = 50.dp)
            ) {
                var selectedFilter by remember { mutableStateOf("") }
                val filterOptions = listOf("진행 중", "완료")

                Row {
                    filterOptions.forEach { option ->
                        Surface(
                            modifier = Modifier
                                .clickable(
                                    interactionSource = null,
                                    indication = null,
                                    onClick = {
                                        CoroutineScope(Dispatchers.Main).launch {
                                            selectedFilter =
                                                if (selectedFilter == option) "" else option
                                            viewModel.filterList(selectedFilter)
                                        }
                                    }
                                )
                                .background(
                                    shape = RoundedCornerShape(97.dp),
                                    color = if (selectedFilter == option) MainColor else MainWhite
                                )
                                .padding(horizontal = 8.dp, vertical = 6.dp),
                            shape = RoundedCornerShape(97.dp),
                            color = Color.Transparent,
                        ) { CustomText2(option) }
                        Spacer(modifier = Modifier.width(4.dp))
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))
                if (roomDeals.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MainBackground)
                    ) {
                        Text(
                            text = "채팅방이 없습니다.",
                            modifier = Modifier.align(Alignment.Center),
                            color = Gray7
                        )
                    }
                } else {
                    LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        items(roomDeals) { deal ->
                            ChatItem(
                                deal = deal,
                                isMyRoom = deal.creatorId == userId,
                                onClick = {
                                    navController.navigate(ChatNavigationItem.ChatRoom.destination + "/${deal.roomId}")
                                },
                                onClickRoomFinish = {
                                    roomId = it
                                    isDialog = true
                                }
                            )
                        }
                        item { Spacer(modifier = Modifier.height(20.dp)) }
                    }
                }
            }
        else
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MainBackground)
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    trackColor = MainColor.copy(alpha = 0.4f),
                    color = MainColor
                )
            }
    }
}