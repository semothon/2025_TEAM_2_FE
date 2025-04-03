package com.example.team2.presentation.roomlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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

@Composable
fun RoomListScreen(
    navController: NavController,
    viewModel: RoomListViewModel = viewModel()
) {
    val rooms by viewModel.filteredRooms.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState("")
    val initialKeywords by viewModel.initialKeywords.collectAsState()
    val additionalKeywords by viewModel.additionalKeywords.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MainBackground)
            .padding(InnerPadding)
    ) {
        Column {
            SearchBar(searchQuery) { }

            Spacer(Modifier.height(16.dp))
            KeywordList(viewModel, initialKeywords, additionalKeywords)

            Spacer(Modifier.height(16.dp))
            LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                items(rooms) { room ->
                    RoomListItem(room) {
                        navController.navigate(HomeNavigationItem.RoomDetail.destination)
                    }
                }
            }
        }

        FloatingActionButton(
            onClick = { navController.navigate(HomeNavigationItem.RoomAdd.destination) },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 80.dp),
            containerColor = Color.Transparent
        ) {
            Image(
                painter = painterResource(R.drawable.room_add_button),
                contentDescription = "add",
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(top = 20.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RoomListPreview() {
    RoomListScreen(rememberNavController())
}
