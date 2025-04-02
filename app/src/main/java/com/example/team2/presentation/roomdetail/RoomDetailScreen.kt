package com.example.team2.presentation.roomdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.team2.presentation.component.BottomButton
import com.example.team2.presentation.component.CustomText4
import com.example.team2.presentation.component.TopBar
import com.example.team2.presentation.roomdetail.component.MemberItem
import com.example.team2.ui.theme.InnerPadding
import com.example.team2.ui.theme.MainBackground
import com.example.team2.ui.theme.MainWhite

@Composable
fun RoomDetailScreen(
    navController: NavController,
    viewModel: RoomDetailViewModel = viewModel()
) {
    val members = viewModel.members.collectAsState()

    LaunchedEffect(Unit) {
        // 방 정보 불러오기
    }
    val restaurantName = "맥도날드"

    Scaffold(
        topBar = { TopBar(restaurantName) { navController.popBackStack() } },
        bottomBar = {
            BottomButton("참여하기", false) { } //참여하기
        }
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .background(MainBackground)
                .padding(it)
                .padding(InnerPadding)
        ) {
            CustomText4("인원 현황")
            Spacer(Modifier.height(8.dp))
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
                    items(members.value) { member ->
                        MemberItem(member)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RoomDetailPreview() {
    RoomDetailScreen(rememberNavController())
}