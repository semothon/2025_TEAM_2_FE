package com.example.team2.presentation.user

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun NotificationSettingScreen(navController: NavController) {
    // 상태 (토글)
    var chatAlarm by remember { mutableStateOf(false) }
    var roomAlarm by remember { mutableStateOf(false) }
    var likeAlarm by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("알림설정") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Text("←") // 아이콘 대신 텍스트 사용
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp, vertical = 32.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            NotificationItem("채팅 알림", chatAlarm) { chatAlarm = it }
            NotificationItem("방 개설 알림", roomAlarm) { roomAlarm = it }
            NotificationItem("좋아요 알림", likeAlarm) { likeAlarm = it }
        }
    }
}

@Composable
fun NotificationItem(label: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label)
        Switch(checked = checked, onCheckedChange = onCheckedChange)
    }
}
