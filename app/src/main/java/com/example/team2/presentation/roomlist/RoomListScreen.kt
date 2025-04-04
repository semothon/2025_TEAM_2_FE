package com.example.team2.presentation.roomlist

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
import androidx.compose.material3.FloatingActionButton
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
import com.example.team2.ui.theme.InnerPadding
import com.example.team2.ui.theme.MainBackground
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun RoomListScreen(
    navController: NavController,
    viewModel: RoomListViewModel = viewModel()
) {
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
            .padding(bottom = 80.dp)
    ) {
        if (isLoading) {
            Column {
                SearchBar(
                    searchQuery = searchQuery,
                    onSearchQueryChanged = { searchQuery = it },
                    onClick = { viewModel.onSearchQueryChanged(searchQuery) }
                )

                Spacer(Modifier.height(16.dp))
                KeywordList(viewModel, initialKeywords, additionalKeywords)

                Spacer(Modifier.height(16.dp))
                LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    items(rooms) { room ->
                        RoomListItem(room) {
                            navController.navigate(
                                HomeNavigationItem.RoomDetail.destination +
                                        "/${room.roomId}/${room.restaurantName}/${room.content}/${room.tagChips}/${room.status}"
                            )
                        }
                    }
                }
            }

            Image(
                painter = painterResource(R.drawable.room_add_button),
                contentDescription = "add",
                modifier = Modifier
                    .padding(bottom = 80.dp)
                    .align(Alignment.BottomEnd)
                    .clickable { navController.navigate(HomeNavigationItem.RoomAdd.destination) }
            )
        } else
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }
}

@Preview(showBackground = true)
@Composable
fun RoomListPreview() {
    RoomListScreen(rememberNavController())
}
