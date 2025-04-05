package com.example.team2.presentation.chatlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.team2.R
import com.example.team2.network.exChatData
import com.example.team2.presentation.chatlist.model.Chat
import com.example.team2.presentation.chatlist.model.ChatStatus

@Composable
fun ChatListScreenPreviewData(chatRooms: List<Chat>) {
    var filterStatus by remember { mutableStateOf<ChatStatus?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(horizontal = 24.dp)
    ) {
        Spacer(modifier = Modifier.height(12.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            ChatFilterButton("진행 중", filterStatus == ChatStatus.IN_PROGRESS) {
                filterStatus = if (filterStatus == ChatStatus.IN_PROGRESS) null else ChatStatus.IN_PROGRESS
            }
            ChatFilterButton("완료", filterStatus == ChatStatus.COMPLETED) {
                filterStatus = if (filterStatus == ChatStatus.COMPLETED) null else ChatStatus.COMPLETED
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        val filteredRooms = when (filterStatus) {
            null -> chatRooms
            else -> chatRooms.filter { it.status == filterStatus }
        }

        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(filteredRooms) { chat ->
                ChatRoomItem(chat)
            }
        }
    }
}


@Composable
fun ChatFilterButton(text: String, selected: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (selected) Color(0xFFFFCC01) else Color.White,
            contentColor = Color.Black
        ),
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
        modifier = Modifier.height(34.dp)
    ) {
        Text(text, fontSize = 12.sp, fontWeight = FontWeight.Medium)
    }
}

@Composable
fun ChatRoomItem(chatRoom: Chat) {
    val borderColor = if (chatRoom.status == ChatStatus.IN_PROGRESS) Color(0xFFFFCC01) else Color.Transparent
    val backgroundColor = if (chatRoom.status == ChatStatus.IN_PROGRESS) Color.White else Color.White

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(2.dp, borderColor, RoundedCornerShape(16.dp))
            .background(backgroundColor, RoundedCornerShape(16.dp))
            .clickable { },
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 프로필들
            Row(horizontalArrangement = Arrangement.spacedBy((-8).dp)) {
                chatRoom.participantProfileImages.take(3).forEach {
                    Image(
                        painter = painterResource(id = it),
                        contentDescription = null,
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .border(1.dp, Color.White, CircleShape)
                    )
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(
                        text = chatRoom.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = if (chatRoom.status == ChatStatus.IN_PROGRESS) Color(0xFF574C4D) else Color(0x99574C4D)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (chatRoom.status == ChatStatus.IN_PROGRESS) "진행 중" else "완료",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = if (chatRoom.status == ChatStatus.IN_PROGRESS) Color(0xFFFFCC01) else Color(0xFFC4C4C4)
                    )
                }
                Text(
                    text = chatRoom.lastMessage,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xCC574C4D),
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            if (chatRoom.unreadCount > 0) {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFFF6B00)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = chatRoom.unreadCount.toString(),
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ChatPreview() {
    ChatListScreenPreviewData(
        chatRooms = exChatData()
    )
}
