package com.example.team2.presentation.roomdetail

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.example.team2.navigation.home.model.HomeToDetail
import com.example.team2.presentation.component.BottomButton
import com.example.team2.presentation.component.CustomText
import com.example.team2.presentation.component.CustomText3
import com.example.team2.presentation.component.CustomText5
import com.example.team2.presentation.component.CustomText6
import com.example.team2.presentation.component.DropDownMenu
import com.example.team2.presentation.component.RowTextAndIcon
import com.example.team2.presentation.component.TopBar
import com.example.team2.presentation.roomdetail.component.MemberItem
import com.example.team2.presentation.roomdetail.model.Member
import com.example.team2.presentation.roomdetail.model.User
import com.example.team2.presentation.roomlist.RoomListViewModel
import com.example.team2.presentation.signup.model.Infos
import com.example.team2.ui.theme.Gray2
import com.example.team2.ui.theme.InnerPadding
import com.example.team2.ui.theme.MainBackground
import com.example.team2.ui.theme.MainColor
import com.example.team2.ui.theme.MainWhite
import com.example.team2.userId
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun RoomDetailScreen(
    navController: NavController,
    room: HomeToDetail,
    viewModel: RoomDetailViewModel = viewModel()
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val isLoading by viewModel.isLoading.collectAsState()
    val members by viewModel.members.collectAsState()
    val isButton by viewModel.isButton.collectAsState()
    val location by viewModel.location.collectAsState()
    val popBack by viewModel.popBack.collectAsState()
    val isDialog by viewModel.isDialog.collectAsState()
    val memberInfo = viewModel.member.collectAsState()

    LaunchedEffect(room.roomId) {
        viewModel.getRoomDetail(room.roomId)
    }
    LaunchedEffect(popBack) {
        if (popBack)
            navController.popBackStack()
    }

    if (isLoading)
    Scaffold(
        topBar = { TopBar(room.roomName) { navController.popBackStack() } },
            bottomBar = {
                if (isButton)
                    BottomButton("참여하기", true) {
                        viewModel.joinRoom(room.roomId)
                    }
            }
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .background(MainBackground)
                .padding(it)
                .padding(InnerPadding)
        ) {
                CustomText6("인원 현황")
                Spacer(Modifier.height(12.dp))
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MainWhite
                    ),
                    shape = RoundedCornerShape(15.dp)
                ) {
                    LazyColumn(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(members) { member ->
                            MemberItem(member, viewModel)
                        }
                    }
                }

                Spacer(Modifier.height(20.dp))
                CustomText6("방 상세")
                Spacer(Modifier.height(12.dp))
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MainWhite),
                    shape = RoundedCornerShape(15.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        CustomText5("방 상세 설명")
                        CustomText5(room.roomContent)
                        CustomText5("방 키워드")
//                        LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
//                            items(room.roomTagChips.drop(4)) { keyword ->
//                                TagChip(keyword)
//                            }
//                        }
                    }
                }

//                if (!isButton) {
//                    val roomOptions = viewModel.roomOptions.collectAsState()
//                    val room = rememberSaveable { mutableStateOf("") }
//                    val roomExpanded = remember { mutableStateOf(false) }
//                    val roomInfo = Infos(
//                        "방 상태",
//                        room,
//                        room,
//                        roomOptions.value,
//                        roomExpanded,
//                        Icons.Default.KeyboardArrowDown
//                    )
//
//                    Spacer(Modifier.height(20.dp))
//                    CustomText6("방 상태")
//                    Spacer(Modifier.height(8.dp))
//                    Column {
//                        RowTextAndIcon(
//                            keyboardController = keyboardController,
//                            text = roomInfo.selectedText.value,
//                            placeholder = roomInfo.placeholder,
//                            icon = roomInfo.icon,
//                            onClickExpanded = { roomInfo.expanded.value = true }
//                        )
//                        DropDownMenu(
//                            options = roomInfo.options,
//                            isExpanded = roomInfo.expanded,
//                            onClickExpanded = { roomInfo.expanded.value = false },
//                            onClickOption = { roomInfo.selectedText.value = it }
//                        )
//                    }
//                }

                Spacer(Modifier.height(20.dp))
                CustomText6("위치")
                Spacer(Modifier.height(12.dp))
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MainWhite),
                    shape = RoundedCornerShape(15.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        CustomText5(location)
                    }
                }

//                if (isDialog)
//                    AlertDialog(
//                        onDismissRequest = { viewModel.isDialogFalse() },
//                        confirmButton = {},
//                        dismissButton = {},
//                        text = {
//                            Column(
//                                horizontalAlignment = Alignment.CenterHorizontally,
//                                modifier = Modifier.padding(20.dp)
//                            ) {
//                                Image(
//                                    painter = painterResource(memberInfo.value.illustration),
//                                    contentDescription = "User Icon",
//                                    modifier = Modifier.size(120.dp)
//                                )
//                                Spacer(modifier = Modifier.width(8.dp))
//                                Column {
//                                    CustomText3(memberInfo.value.nickName)
//                                    Spacer(modifier = Modifier.width(8.dp))
//                                    CustomText5(memberInfo.value.univInfo[2].toString())
//                                    Spacer(modifier = Modifier.width(8.dp))
//                                    CustomText5(memberInfo.value.univInfo[1].toString() + "학번")
//                                    Spacer(modifier = Modifier.height(20.dp))
//                                    CustomText5(memberInfo.value.favoriteCount.toString())
//                                }
//
//                                Spacer(modifier = Modifier.height(20.dp))
//                                Row(
//                                    modifier = Modifier.fillMaxWidth(),
//                                    horizontalArrangement = Arrangement.SpaceEvenly
//                                ) {
//                                    Button(
//                                        onClick = { viewModel.blockUser(memberInfo.value.userId) },
//                                        modifier = Modifier
//                                            .weight(1f)
//                                            .height(40.dp),
//                                        colors = ButtonDefaults.buttonColors(containerColor = Gray2)
//                                    ) {
//                                        CustomText3(text = "차단하기", alpha = 0.7f)
//                                    }
//                                    Spacer(modifier = Modifier.width(8.dp))
//                                    Button(
//                                        onClick = { viewModel.isDialogFalse() },
//                                        modifier = Modifier
//                                            .weight(1f)
//                                            .height(40.dp),
//                                        colors = ButtonDefaults.buttonColors(containerColor = MainColor)
//                                    ) {
//                                        CustomText3(text = "채팅 걸기")
//                                    }
//                                }
//                            }
//                        }
//                    )

        }
    }
    else
        CircularProgressIndicator()
}