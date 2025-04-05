package com.example.team2.presentation.chatroom

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Send
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatRoomScreen(
    roomName: String = "맥도날드",
) {
    val sheetState = rememberModalBottomSheetState()
    val showSheet = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        ChatRoomTopBar(
            title = roomName,
            onMoreClick = { showSheet.value = true }
        )

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            ChatMessageList()
        }

        ChatInputField()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatRoomTopBar(title: String, onMoreClick: () -> Unit) {
    TopAppBar(
        title = {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = title,
                    modifier = Modifier.align(Alignment.Center),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 17.sp,
                    color = Color(0xFF574C4D)
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = { /* 뒤로가기 */ }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.Black)
            }
        },
        actions = {
            IconButton(onClick = { /* 메뉴 */ }) {
                Icon(Icons.Default.MoreVert, contentDescription = "More", tint = Color.Black)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
    )
}

@Composable
fun ChatMessageList(modifier: Modifier = Modifier) {
    val messages = listOf(
        ChatMessage("꾸벅이", "글자 테스트", isMe = false),
        ChatMessage("꾸벅이", "글자 테스트 글자 테스트 글자 테스트.", isMe = false),
        ChatMessage("me", "글자 테스트", isMe = true),
        ChatMessage("me", "글자 테스트 글자 테스트 글자 테스트.\n글자 테스트 글자 테스트...", isMe = true)
    )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            reverseLayout = true
        ) {
            items(messages.reversed()) { msg ->
                ChatMessageItem(msg)
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                Spacer(modifier = Modifier.height(12.dp))
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Surface(
                        color = Color(0xFFF0F0F0),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = "2025년 5월 12일",
                            fontSize = 12.sp,
                            color = Color.Gray,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

}


data class ChatMessage(val sender: String, val content: String, val isMe: Boolean)

@Composable
fun ChatMessageItem(message: ChatMessage) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = if (message.isMe) Alignment.End else Alignment.Start
    ) {
        if (!message.isMe) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.profile_illustration_1),
                    contentDescription = "프로필",
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(Color.LightGray)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(message.sender, fontSize = 12.sp, color = Color.Gray)
            }
        }

        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = if (message.isMe) Arrangement.End else Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (message.isMe) {
                Text(
                    text = "2:12 PM", // 👉 임시 시간
                    fontSize = 10.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(end = 4.dp)
                )
                Surface(
                    color = Color(0xFFFFCC01),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = message.content,
                        modifier = Modifier.padding(12.dp),
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                }
            } else {
                Surface(
                    color = Color.White,
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = message.content,
                        modifier = Modifier.padding(12.dp),
                        color = Color(0xFF574C4D),
                        fontSize = 14.sp
                    )
                }
                Text(
                    text = "2:12 PM", // 👉 임시 시간
                    fontSize = 10.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
        }
    }
}


@Composable
fun ChatInputField() {
    var text by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            placeholder = { Text("메세지 보내기.") },
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(20.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFFF5F5F5),
                focusedContainerColor = Color(0xFFF5F5F5)
            )
        )
        Spacer(modifier = Modifier.width(8.dp))
        Button(
            onClick = { /* 전송 처리 */ },
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFCC01)),
            contentPadding = PaddingValues(0.dp),
            modifier = Modifier.size(42.dp)
        ) {
            Icon(Icons.Default.Send, contentDescription = "보내기", tint = Color(0xFF574C4D))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChatRoomScreenPreview() {
    ChatRoomScreen(roomName = "맥도날드")
}

