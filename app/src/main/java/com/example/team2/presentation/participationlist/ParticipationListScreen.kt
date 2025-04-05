package com.example.team2.presentation.participationlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.team2.R
import com.example.team2.model.Transaction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParticipationListScreen(
    viewModel: TransactionViewModel = TransactionViewModel(),
    navController: NavController
) {
    val transactions by viewModel.transactions.collectAsState()
    val (filterText, _) = viewModel.filter.collectAsState().value

    val filteredTransactions = when (filterText) {
        "진행 중" -> transactions.filter { it.isOngoing }
        "완료" -> transactions.filter { !it.isOngoing }
        else -> transactions
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "거래내역",
                            modifier = Modifier.align(Alignment.Center),
                            fontSize = 17.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF574C4D)
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.ic_notification),
                            contentDescription = "알림",
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .padding(end = 18.dp)
                                .size(24.dp),
                            tint = Color.Unspecified
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
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
            Spacer(modifier = Modifier.height(12.dp))

            LazyColumn(modifier = Modifier.padding(horizontal = 24.dp)) {
                itemsIndexed(filteredTransactions) { index, transaction ->
                    TransactionItem(
                        transaction = transaction,
                        index = index,
                        onComplete = { viewModel.completeTransaction(it) },
                        onClick = {
                            navController?.navigate("room_detail")
                        }
                    )
                    if (index < filteredTransactions.lastIndex) {
                        Spacer(modifier = Modifier.height(15.dp))
                    }
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
            .padding(horizontal = 24.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        filterOptions.forEach { option ->
            Button(
                onClick = { viewModel.setFilter(option) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedFilter == option) Color(0xFFFFCC01) else Color.White
                ),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .height(34.dp)
            ) {
                Text(
                    option,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Composable
fun TransactionItem(
    transaction: Transaction,
    index: Int,
    onComplete: (Int) -> Unit,
    onClick: () -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {},
            dismissButton = {},
            text = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    Text(
                        text = "거래를 완료하시겠습니까?",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF574C4D),
                        modifier = Modifier
                            .padding(top = 4.dp, bottom = 2.dp)
                            .align(Alignment.CenterHorizontally)
                    )

                    Divider(color = Color(0xFFE0E0E0), thickness = 1.dp)

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        TextButton(
                            onClick = { showDialog = false },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("취소", color = Color(0xFF574C4D))
                        }

                        Box(
                            modifier = Modifier
                                .width(1.dp)
                                .fillMaxHeight()
                                .background(Color(0xFFE0E0E0))
                        )

                        TextButton(
                            onClick = {
                                showDialog = false
                                onComplete(transaction.id)
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("완료", color = Color(0xFFFFCC01), fontWeight = FontWeight.Bold)
                        }
                    }
                }
            },
            containerColor = Color.White,
            shape = RoundedCornerShape(16.dp)
        )
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .border(
                width = if (transaction.isOngoing) 2.dp else 0.dp,
                color = if (transaction.isOngoing) Color(0xFFFFCC01) else Color.Transparent,
                shape = RoundedCornerShape(16.dp)
            ),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
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
                            fontSize = 15.sp,
                            color = if (transaction.isOngoing) Color(0xFF574C4D) else Color(0x99574C4D)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = if (transaction.isOngoing) "진행 중" else "완료",
                            fontWeight = FontWeight.Medium,
                            fontSize = 12.sp,
                            color = if (transaction.isOngoing) Color(0xFFFFCC01) else Color(0xFFC4C4C4)
                        )
                    }
                    Text(
                        text = transaction.roomDesc,
                        fontWeight = FontWeight.Medium,
                        fontSize = 13.sp,
                        color = Color(0xCC574C4D),
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
                        modifier = Modifier
                            .width(150.dp)
                            .height(48.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFFCC01),
                            contentColor = Color.Black
                        )
                    ) {
                        Text(
                            "완료하기",
                            color = Color(0xFF574C4D),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 13.sp
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ParticipationListPreview() {
    val navController = rememberNavController()
    ParticipationListScreen(navController = navController)
}

