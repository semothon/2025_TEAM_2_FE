package com.example.team2.presentation.roomadd

import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.team2.presentation.component.CustomOutlinedTextField
import com.example.team2.presentation.component.CustomRadioButton
import com.example.team2.presentation.component.CustomText3
import com.example.team2.presentation.component.CustomText4
import com.example.team2.presentation.component.DropDownMenu
import com.example.team2.presentation.component.RowTextAndIcon
import com.example.team2.presentation.component.TopBar
import com.example.team2.ui.theme.Gray2
import com.example.team2.ui.theme.Gray3
import com.example.team2.ui.theme.MainBackground
import com.example.team2.ui.theme.MainColor

@Composable
fun RoomAddScreen(
    navController: NavController,
    viewModel: RoomAddViewModel = viewModel()
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var restaurantName by rememberSaveable { mutableStateOf("") }
    var roomContent by rememberSaveable { mutableStateOf("") }
    var location by rememberSaveable { mutableStateOf("") }
    var isTogether by rememberSaveable { mutableStateOf("같이 먹기") }
    var gender by rememberSaveable { mutableStateOf("상관 없어요") }

    val foodOptions = viewModel.foodOptions.collectAsState()
    val foodCategory = rememberSaveable { mutableStateOf("카테고리를 선택하세요.") }
    val foodCategoryExpanded = remember { mutableStateOf(false) }

    val memberCountOptions = viewModel.memberCountOptions.collectAsState()
    val memberCount = rememberSaveable { mutableStateOf("희망인원을 선택하세요.") }
    val memberCountExpanded = remember { mutableStateOf(false) }

    Scaffold(
        topBar = { TopBar("방 개설") { navController.popBackStack() } },
        bottomBar = {
            Button(
                onClick = {
//                    viewModel.makeRoom(
//                        AddRoom(
//                            roomTitle,
//                            roomContent,
//                            selectedTogether ?: true,
//                            roomTotalPeopleCount,
//                            selectedGender,
//                            roomLocation
//                        )
//                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .background(color = MainColor), // else Gray2),
//                enabled = restaurantName.isNotEmpty() && roomContent.isNotEmpty() && location.isNotEmpty(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                contentPadding = PaddingValues(0.dp)
            ) {
                CustomText4("완료")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MainBackground)
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Spacer(Modifier.height(20.dp))
            Row {
                CustomText3("식당 이름")
                Spacer(Modifier.width(4.dp))
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "check",
                    tint = if (restaurantName.isNotEmpty()) MainColor else Gray3,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(Modifier.height(8.dp))
            CustomOutlinedTextField(
                value = restaurantName,
                placeholder = "식당 이름을 입력하세요.",
                onValueChanged = { restaurantName = it }
            )

            Spacer(Modifier.height(20.dp))
            Row {
                CustomText3("상세 설명")
                Spacer(Modifier.width(4.dp))
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "check",
                    tint = if (roomContent.isNotEmpty()) MainColor else Gray3,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(Modifier.height(8.dp))
            CustomOutlinedTextField(
                value = roomContent,
                placeholder = "상세설명을 입력하세요.",
                onValueChanged = { roomContent = it },
                maxLines = 3
            )

            Column {
                Spacer(Modifier.height(20.dp))
                CustomText3("음식 카테고리")
                Spacer(Modifier.height(8.dp))
                RowTextAndIcon(
                    keyboardController = keyboardController,
                    text = foodCategory.value,
                    icon = Icons.Default.KeyboardArrowDown,
                    onClickExpanded = { foodCategoryExpanded.value = true }
                )
                DropDownMenu(
                    options = foodOptions.value,
                    isExpanded = foodCategoryExpanded,
                    onClickExpanded = { foodCategoryExpanded.value = false },
                    onClickOption = { foodCategory.value = it }
                )
            }

            Column {
                Spacer(Modifier.height(20.dp))
                CustomText3("희망 인원 수")
                Spacer(Modifier.height(8.dp))
                RowTextAndIcon(
                    keyboardController = keyboardController,
                    text = memberCount.value,
                    icon = Icons.Default.KeyboardArrowDown,
                    onClickExpanded = { memberCountExpanded.value = true }
                )
                DropDownMenu(
                    options = memberCountOptions.value,
                    isExpanded = memberCountExpanded,
                    onClickExpanded = { memberCountExpanded.value = false },
                    onClickOption = { memberCount.value = it }
                )
            }

            Spacer(Modifier.height(20.dp))
            CustomText3("같이 먹기 여부")
            Spacer(Modifier.height(8.dp))
            Row {
                CustomRadioButton(isTogether, "같이 먹기", Modifier.weight(1f)) { isTogether = "같이 먹기" }
                CustomRadioButton(isTogether, "따로 먹기", Modifier.weight(1f)) { isTogether = "따로 먹기" }
            }

            Spacer(Modifier.height(20.dp))
            CustomText3("성별 지정")
            Spacer(Modifier.height(8.dp))
            Row {
                CustomRadioButton(gender, "상관 없어요", Modifier.weight(1f)) { gender = "상관 없어요" }
                CustomRadioButton(gender, "동성만", Modifier.weight(1f)) { gender = "동성만" }
            }

            Spacer(Modifier.height(20.dp))
            CustomText3("추천 키워드")
            // 추천 키워드 추가

            Spacer(Modifier.height(20.dp))
            Row {
                CustomText3("위치")
                Spacer(Modifier.width(4.dp))
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "check",
                    tint = if (location.isNotEmpty()) MainColor else Gray3,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(Modifier.height(8.dp))
            CustomOutlinedTextField(
                value = location,
                placeholder = "위치를을 입력하세요.",
                onValueChanged = { location = it }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RoomAddPreview() {
    RoomAddScreen(rememberNavController())
}