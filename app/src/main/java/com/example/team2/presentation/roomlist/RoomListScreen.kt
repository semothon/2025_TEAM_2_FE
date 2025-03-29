package com.example.team2.presentation.roomlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.team2.navigation.RoomListNavigationGraph
import com.example.team2.presentation.roomlist.model.Room

@Composable
fun RoomListScreen(
    navController: NavController,
    viewModel: RoomListViewModel = RoomListViewModel()
) {
    val rooms by viewModel.rooms.collectAsState(emptyList())
    val searchQuery by viewModel.searchQuery.collectAsState("")
    val allKeywords by viewModel.allKeywords.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column {
            TextField(
                value = searchQuery,
                onValueChange = { viewModel.onSearchQueryChanged(searchQuery) },
                label = { Text(text = "검색") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(8.dp))
            RoomFilter(viewModel, allKeywords)

            Spacer(Modifier.height(8.dp))
            HorizontalDivider()

            LazyColumn {
                items(rooms) { room ->
                    RoomItem(room)
                }
            }
        }

        FloatingActionButton(
            onClick = { navController.navigate("AddRoom") },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 60.dp)
        ) { Icon(imageVector = Icons.Default.Add, contentDescription = "") }
    }
}

@Composable
fun RoomFilter(viewModel: RoomListViewModel, allKeywords: List<String>) {
    val selectedKeywords by viewModel.selectedKeywords.collectAsState()

    Text("추천 키워드")
    LazyRow {
        items(allKeywords) { keyword ->
            FilterChip(
                keyword = keyword,
                isSelected = selectedKeywords.contains(keyword),
                onClick = {
                    if (selectedKeywords.contains(keyword)) {
                        viewModel.removeKeyword(keyword)
                    } else {
                        viewModel.addKeyword(keyword)
                    }
                }
            )
        }
    }

    Row {
        Text(text = "선택된 키워드: ")
        if (selectedKeywords.isNotEmpty())
            Text(text = selectedKeywords.joinToString(", "))
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = { viewModel.emptyListKeywords() },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
        ) {
            Text("초기화", color = Color.White)
        }
        Button(
            onClick = {
                // 적용할 동작을 여기에 추가
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
        ) {
            Text("적용하기", color = Color.White)
        }
    }
}

@Composable
fun FilterChip(keyword: String, isSelected: Boolean, onClick: () -> Unit) {
    Surface(
        modifier = Modifier.padding(4.dp),
        shape = RoundedCornerShape(16.dp),
        color = if (isSelected) Color.Blue else Color.LightGray,
    ) {
        Button(
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        ) {
            Text(
                text = keyword,
                color = if (isSelected) Color.White else Color.Black
            )
        }
    }
}

@Composable
fun RoomItem(room: Room) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = room.name)
        Text(text = room.details)
    }
    HorizontalDivider()
}

@Preview(showBackground = true)
@Composable
fun RoomListPreview() {
    RoomListScreen(rememberNavController())
}
