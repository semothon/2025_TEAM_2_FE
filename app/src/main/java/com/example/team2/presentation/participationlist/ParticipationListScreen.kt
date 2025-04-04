package com.example.team2.presentation.participationlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.team2.model.Transaction
import com.example.team2.presentation.component.CustomText2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParticipationListScreen(viewModel: TransactionViewModel = TransactionViewModel()) {
    val transactions by viewModel.transactions.collectAsState()
    val (filterText, _) = viewModel.filter.collectAsState().value

    val filteredTransactions = when (filterText) {
        "진행 중" -> transactions.filter { it.isOngoing }
        "완료" -> transactions.filter { !it.isOngoing }
        else -> transactions
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("거래내역", fontSize = 20.sp, fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.White)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F5F5))
                .padding(paddingValues)
        ) {
            Spacer(modifier = Modifier.height(12.dp))
            TransactionFilter(viewModel)

            LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
                items(filteredTransactions.size) { index ->
                    val transaction = filteredTransactions[index]
                    TransactionItem(
                        transaction = transaction,
                        index = index,
                        onComplete = { viewModel.completeTransaction(it) }
                    )
                }
            }
        }
    }
}

@Composable
fun TransactionFilter(viewModel: TransactionViewModel) {
    val filterOptions = listOf("진행 중", "완료")
    val (selectedFilter, _) = viewModel.filter.collectAsState().value

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        filterOptions.forEach { option ->
            Button(
                onClick = { viewModel.setFilter(option) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedFilter == option) Color.Yellow else Color.White
                ),
                modifier = Modifier.padding(horizontal = 4.dp)
            ) {
                Text(option, color = Color.Black)
            }
        }
    }
}

@Composable
fun TransactionItem(transaction: Transaction, index: Int, onComplete: (Int) -> Unit) {
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("거래를 완료하시겠습니까?", fontSize = 16.sp) },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDialog = false
                        onComplete(transaction.id)
                    }
                ) {
                    Text("완료", color = Color(0xFFFFC107))
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("취소", color = Color.Gray)
                }
            },
            containerColor = Color.White,
            shape = RoundedCornerShape(16.dp)
        )
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(
                width = if (transaction.isOngoing) 2.dp else 0.dp,
                color = if (transaction.isOngoing) Color.Yellow else Color.Transparent,
                shape = RoundedCornerShape(8.dp)
            ),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 👤 프로필 최대 3개 (2+1), 4명이면 +버튼
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    val displayProfiles = transaction.participants.take(3)
                    val chunked = displayProfiles.chunked(2)

                    chunked.forEachIndexed { rowIndex, rowProfiles ->
                        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            rowProfiles.forEach { profile ->
                                Image(
                                    painter = painterResource(id = profile),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clip(CircleShape)
                                        .border(1.dp, Color.Gray, CircleShape)
                                )
                            }

                            if (rowIndex == 1 && transaction.participants.size > 3) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_plus),
                                    contentDescription = "더보기",
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clip(CircleShape)
                                )
                            }
                        }
                    }
                }

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                ) {
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text(
                            text = transaction.roomName,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = if (transaction.isOngoing) "진행 중" else "완료",
                            fontSize = 12.sp,
                            color = if (transaction.isOngoing) Color(0xFFFFC107) else Color.Gray
                        )
                    }
                    Text(
                        text = transaction.roomDesc,
                        fontSize = 14.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }

            if (index == 0 && transaction.isOngoing) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = { showDialog = true },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Yellow,
                            contentColor = Color.Black
                        )
                    ) {
                        Text("완료하기")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ParticipationListPreview() {
    ParticipationListScreen()
}
