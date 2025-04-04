package com.example.team2.presentation.chatlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.team2.presentation.chatlist.model.Chat
import com.example.team2.presentation.chatlist.model.ChatStatus

@Composable
fun ChatListScreen(viewModel: ChatListViewModel = ChatListViewModel()) {
    val chatRooms by viewModel.chatRooms.collectAsState(emptyList())
    var filterStatus by remember { mutableStateOf<ChatStatus?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(bottom = 80.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(onClick = { filterStatus = null }) { Text("전체") }
            Button(onClick = { filterStatus = ChatStatus.IN_PROGRESS }) { Text("진행중") }
            Button(onClick = { filterStatus = ChatStatus.COMPLETED }) { Text("완료") }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp)
        ) {
            val filteredChatRooms = when (filterStatus) {
                null -> chatRooms
                else -> viewModel.filterChatRooms(filterStatus)
            }

            items(filteredChatRooms) { chatRoom ->
                ChatRoomItem(chatRoom)
            }
        }
    }
}

@Composable
fun ChatRoomItem(chatRoom: Chat) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
            .clickable {
                //
            }
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Default.AccountCircle,
                contentDescription = "Profile",
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = chatRoom.name)
                Text(text = chatRoom.lastMessage)
            }
            Text(
                text = when (chatRoom.status) {
                    ChatStatus.IN_PROGRESS -> "진행중"
                    ChatStatus.COMPLETED -> "완료"
                },
                color = if (chatRoom.status == ChatStatus.IN_PROGRESS) Color.Blue else Color.Gray,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChatPreview() {
    ChatListScreen()
}
