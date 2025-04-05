package com.example.team2.presentation.roomadd

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.team2.presentation.component.BottomButton
import com.example.team2.presentation.component.CustomOutlinedTextField
import com.example.team2.presentation.component.CustomRadioButton
import com.example.team2.presentation.component.CustomText3
import com.example.team2.presentation.component.DropDownMenu
import com.example.team2.presentation.component.RowTextAndIcon
import com.example.team2.presentation.component.TopBar
import com.example.team2.presentation.roomadd.model.RoomDetail
import com.example.team2.presentation.roomlist.component.KeywordChip
import com.example.team2.ui.theme.Gray3
import com.example.team2.ui.theme.InnerPadding
import com.example.team2.ui.theme.MainBackground
import com.example.team2.ui.theme.MainColor
import com.example.team2.ui.theme.MainWhite

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RoomAddScreen(
    navController: NavController,
    viewModel: RoomAddViewModel = viewModel()
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val popBack by viewModel.popBack.collectAsState()
    val keywords = viewModel.keywordList.collectAsState()
    val selectedKeywords by viewModel.selectedKeywords.collectAsState()

    var restaurantName by rememberSaveable { mutableStateOf("") }
    var roomContent by rememberSaveable { mutableStateOf("") }
    var location by rememberSaveable { mutableStateOf("") }
    var userCost by rememberSaveable { mutableStateOf("") }
    var totalCost by rememberSaveable { mutableStateOf("") }
    var isTogether by rememberSaveable { mutableStateOf(true) }
    var gender by rememberSaveable { mutableStateOf(true) }
    var isButton by rememberSaveable { mutableStateOf(false) }

    val foodOptions = viewModel.foodOptions.collectAsState()
    val foodCategory = rememberSaveable { mutableStateOf("") }
    val foodCategoryExpanded = remember { mutableStateOf(false) }

    val memberCountOptions = viewModel.memberCountOptions.collectAsState()
    val memberCount = rememberSaveable { mutableStateOf("") }
    val memberCountExpanded = remember { mutableStateOf(false) }

    LaunchedEffect(popBack) {
        if (popBack)
            navController.popBackStack()
    }

    Scaffold(
        topBar = { TopBar("방 개설") { navController.popBackStack() } },
        bottomBar = {
            if (restaurantName.isNotEmpty() && roomContent.isNotEmpty() && foodCategory.value.isNotEmpty() && memberCount.value.isNotEmpty() && location.isNotEmpty() && userCost.isNotEmpty() && totalCost.isNotEmpty())
                isButton = true
            BottomButton("완료", isButton) {
                val hashTags = mutableListOf(
                    foodCategory.value,
                    if (isTogether) "같이 먹을래요" else "따로 먹을래요",
                    if (gender) "상관없음" else "동성만"
                )
                hashTags += selectedKeywords
                viewModel.makeRoom(
                    RoomDetail(
                        restaurantName = restaurantName,
                        content = roomContent,
                        foodCategory = foodCategory.value,
                        totalPeople = memberCount.value.toInt(),
                        isTogether = isTogether,
                        gender = gender,
                        hashTags = hashTags,
                        location = location,
                        userCost = userCost.toInt(),
                        totalCost = totalCost.toInt()
                    )
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MainBackground)
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
                .padding(InnerPadding)
        ) {
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
                    placeholder = "음식 카테고리를 선택하세요.",
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
                    placeholder = "희망 인원 수를 선택하세요.",
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
                CustomRadioButton(isTogether, "같이 먹기", Modifier.weight(1f)) { isTogether = true }
                CustomRadioButton(!isTogether, "따로 먹기", Modifier.weight(1f)) { isTogether = false }
            }

            Spacer(Modifier.height(20.dp))
            CustomText3("성별 지정")
            Spacer(Modifier.height(8.dp))
            Row {
                CustomRadioButton(gender, "상관 없어요", Modifier.weight(1f)) { gender = true }
                CustomRadioButton(!gender, "동성만", Modifier.weight(1f)) { gender = false }
            }

            Spacer(Modifier.height(20.dp))
            CustomText3("추천 키워드")
            Spacer(Modifier.height(8.dp))
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                keywords.value.forEach { keyword ->
                    val color =
                        if (selectedKeywords.contains(keyword)) MainColor
                        else MainWhite
                    KeywordChip(keyword, color) {
                        viewModel.editKeyword(keyword)
                    }
                }
            }

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
                placeholder = "위치를 입력하세요.",
                onValueChanged = { location = it }
            )

            Spacer(Modifier.height(20.dp))
            Row {
                CustomText3("주문 희망 금액")
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
                value = totalCost,
                placeholder = "주문 희망 금액",
                isNumber = true,
                onValueChanged = { totalCost = it }
            )
            Spacer(Modifier.height(8.dp))
            CustomOutlinedTextField(
                value = userCost,
                placeholder = "내가 지불할 금액",
                isNumber = true,
                onValueChanged = { userCost = it }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RoomAddPreview() {
    RoomAddScreen(rememberNavController())
}