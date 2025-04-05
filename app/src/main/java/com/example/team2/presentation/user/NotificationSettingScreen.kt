package com.example.team2.presentation.user

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.team2.R

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun NotificationSettingScreen(navController: NavController) {
    var chatAlarm by remember { mutableStateOf(false) }
    var roomAlarm by remember { mutableStateOf(false) }
    var likeAlarm by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "알림설정",
                            modifier = Modifier.align(Alignment.Center),
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_back_arrow),
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    scrolledContainerColor = Color.White,
                    navigationIconContentColor = Color.Black,
                    titleContentColor = Color.Black
                ),
                scrollBehavior = null
            )
        },
        containerColor = Color.White
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp, vertical = 16.dp),
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
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge
        )
        CustomSwitch(checked = checked, onCheckedChange = onCheckedChange)
    }
}

@Composable
fun CustomSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val imageRes = if (checked) R.drawable.switch_on else R.drawable.switch_off

    Image(
        painter = painterResource(id = imageRes),
        contentDescription = if (checked) "On" else "Off",
        modifier = modifier
            .clickable { onCheckedChange(!checked) }
    )
}



@Preview(showBackground = true)
@Composable
fun NotificationSettingScreenPreview() {
    val navController = rememberNavController()
    NotificationSettingScreen(navController = navController)
}