package com.example.team2.presentation.roomlist

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.team2.R
import com.example.team2.navigation.home.HomeNavigationItem
import com.example.team2.presentation.component.SearchBar
import com.example.team2.presentation.roomlist.component.KeywordList
import com.example.team2.ui.theme.Gray7
import com.example.team2.ui.theme.InnerPadding
import com.example.team2.ui.theme.MainBackground
import com.example.team2.ui.theme.MainColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun RoomListScreen(
    navController: NavController,
    viewModel: RoomListViewModel = viewModel()
) {
    val context = LocalContext.current
    val isLoading by viewModel.isLoading.collectAsState()
    val rooms by viewModel.filteredRooms.collectAsState()
    var searchQuery by remember { mutableStateOf("") }
    val initialKeywords by viewModel.initialKeywords.collectAsState()
    val additionalKeywords by viewModel.additionalKeywords.collectAsState()

    fun fetchData() {
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.fetchRoomListData()
        }
    }

    LaunchedEffect(Unit) {
        fetchData()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MainBackground)
            .padding(InnerPadding)
            .padding(bottom = 50.dp)
    ) {
        Column {
            SearchBar(
                searchQuery = searchQuery,
                onSearchQueryChanged = { searchQuery = it },
                onClick = {
                    if (searchQuery.length < 2)
                        Toast.makeText(context, "두 글자 이상 입력해주세요.", Toast.LENGTH_SHORT).show()
                    else
                        viewModel.onSearchQueryChanged(searchQuery)
                }
            )

            Spacer(Modifier.height(16.dp))
            KeywordList(viewModel, initialKeywords, additionalKeywords)

            if (isLoading) {
                Spacer(Modifier.height(16.dp))
                if (rooms.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MainBackground)
                    ) {
                        Text(
                            text = "방이 없습니다.",
                            modifier = Modifier.align(Alignment.Center),
                            color = Gray7
                        )
                    }
                } else {
                    LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        items(rooms) { room ->
                            RoomListItem(room) {
                                navController.navigate(
                                    HomeNavigationItem.RoomDetail.destination
                                            + "/${room.roomId}/${room.restaurantName}/${room.content}/${room.tagChips}/${room.status}/${room.totalCost}"
                                )
                            }
                        }
                        item { Spacer(modifier = Modifier.height(20.dp)) }
                    }
                }
            } else
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

        Image(
            painter = painterResource(R.drawable.room_add_button),
            contentDescription = "add",
            modifier = Modifier
                .padding(bottom = 10.dp)
                .align(Alignment.BottomEnd)
                .clickable(
                    interactionSource = null,
                    indication = null,
                    onClick = { navController.navigate(HomeNavigationItem.RoomAdd.destination) }
                )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RoomListPreview() {
    RoomListScreen(rememberNavController())
}
