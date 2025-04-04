package com.example.team2.presentation.participationlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.team2.presentation.component.TopBar
import com.example.team2.ui.theme.InnerPadding
import com.example.team2.ui.theme.MainBackground

@Composable
fun ParticipationListScreen(
    navController: NavController,
    viewModel: ParticipationListViewModel = viewModel()
) {
    val isLoading by viewModel.isLoading.collectAsState()
    val roomDeals = viewModel.roomDeals.collectAsState()

    LaunchedEffect(Unit) { viewModel.getRoomDeals() }

    Scaffold(
        topBar = { TopBar("거래내역") { navController.popBackStack() } },
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .background(MainBackground)
                .padding(it)
                .padding(InnerPadding)
                .padding(bottom = 80.dp)
        ) {
            LazyColumn{
                items(roomDeals.value){ deal ->
                    ParticipationItem(deal) {

                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ParticipationListPreview() {
    ParticipationListScreen(rememberNavController())
}