package com.example.team2.presentation.chatroom

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.team2.presentation.chatroom.model.ChatHistoryDetail
import com.example.team2.presentation.component.TopBar
import com.example.team2.ui.theme.Brown2
import com.example.team2.ui.theme.Gray1
import com.example.team2.ui.theme.MainBackground
import com.example.team2.ui.theme.MainColor
import com.example.team2.userId
import kotlinx.coroutines.delay
import java.time.LocalDate
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@Composable
fun ChatRoomScreen(
    navController: NavController,
    roomId: String,
    viewModel: ChatRoomViewModel = viewModel()
) {
    val isLoading by viewModel.isLoading.collectAsState()
    val chatHistory by viewModel.chatHistory.collectAsState()
    val listState = rememberLazyListState()

    LaunchedEffect(Unit) {
        while (true) {
            viewModel.getChatHistory(roomId)
            delay(1000)
        }
    }
    LaunchedEffect(chatHistory) {
        if (chatHistory.isNotEmpty()) {
            listState.animateScrollToItem(chatHistory.size - 1)
        }
    }

    Scaffold(
        topBar = {
            TopBar("채팅", true) {
                navController.popBackStack()
            }
        }
    ) { paddingValues ->
        if (isLoading)
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(MainBackground)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {
                    ChatMessageList(chatHistory, listState)
                }

                ChatInputField(roomId, viewModel)
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

@Composable
fun ChatMessageList(chatHistory: List<ChatHistoryDetail>, listState: LazyListState) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        state = listState
    ) {
        item {
            Spacer(modifier = Modifier.height(12.dp))
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text(
                    text = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy년 M월 d일"))
                        .toString(),
                    fontSize = 12.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }

        items(chatHistory) { chat ->
            ChatMessageItem(chat)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun ChatMessageItem(message: ChatHistoryDetail) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = if (message.senderInfo.userId == userId) Alignment.End else Alignment.Start
    ) {
        if (message.senderInfo.userId != userId) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(message.senderInfo.illustration),
                    contentDescription = "프로필",
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(message.senderInfo.nickname, fontSize = 12.sp, color = Color.Gray)
            }
        }

        val dateTime = ZonedDateTime.parse(message.timestamp, DateTimeFormatter.ISO_DATE_TIME)
        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = if (message.senderInfo.userId == userId) Arrangement.End else Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (message.senderInfo.userId == userId) {
                Text(
                    text = "${dateTime.hour}시 ${dateTime.minute}분",
                    fontSize = 10.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(end = 4.dp)
                )
                Surface(
                    color = MainColor,
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = message.message,
                        modifier = Modifier.padding(12.dp),
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                }
            } else {
                Spacer(modifier = Modifier.width(48.dp))
                Surface(
                    color = Color.White,
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = message.message,
                        modifier = Modifier.padding(12.dp),
                        color = Brown2,
                        fontSize = 14.sp
                    )
                }
                Text(
                    text = "${dateTime.hour}시 ${dateTime.minute}분",
                    fontSize = 10.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
        }
    }
}

@Composable
fun ChatInputField(roomId: String, viewModel: ChatRoomViewModel) {
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
                unfocusedContainerColor = Gray1,
                focusedContainerColor = Gray1
            )
        )
        Spacer(modifier = Modifier.width(8.dp))
        Button(
            onClick = {
                viewModel.sendMessage(roomId, text)
            },
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(containerColor = MainColor),
            contentPadding = PaddingValues(0.dp),
            modifier = Modifier.size(42.dp)
        ) {
            Icon(Icons.AutoMirrored.Filled.Send, contentDescription = "보내기", tint = Brown2)
        }
    }
}