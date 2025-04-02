package com.example.team2.presentation.user

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.team2.R
import com.example.team2.presentation.user.model.ProfileInfo
import com.example.team2.ui.theme.Gray1
import com.example.team2.ui.theme.Gray4
import com.example.team2.ui.theme.Gray5
import com.example.team2.ui.theme.MainColor
import com.example.team2.ui.theme.MainWhite


@Composable
fun EditProfileScreen(
    viewModel: EditProfileViewModel,
    navController: NavController
) {
    val profileInfo by viewModel.profileInfo.collectAsState()

    var nickname by rememberSaveable { mutableStateOf(profileInfo.nickname) }
    var major by rememberSaveable { mutableStateOf(profileInfo.major) }
    var year by rememberSaveable { mutableStateOf(profileInfo.year) }
    var gender by rememberSaveable { mutableStateOf(profileInfo.gender) }
    var address by rememberSaveable { mutableStateOf(profileInfo.address) }

    var majorExpanded by remember { mutableStateOf(false) }
    var yearExpanded by remember { mutableStateOf(false) }
    var addressExpanded by remember { mutableStateOf(false) }

    val majorOptions by viewModel.majorOptions.collectAsState()
    val yearOptions by viewModel.yearOptions.collectAsState()
    val addressOptions by viewModel.addressOptions.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 24.dp)
    ) {
        Text("프로필 수정", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(20.dp))

        val image = painterResource(R.drawable.profile_illustration_1)
        Box() {
            Image(
                painter = image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alpha = 0.5F
            )
        }

        OutlinedTextField(
            value = nickname,
            onValueChange = { nickname = it },
            label = { Text("닉네임") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { majorExpanded = true }
                .padding(12.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
        ) {
            Text(text = major)
        }
        DropdownMenu(
            expanded = majorExpanded,
            onDismissRequest = { majorExpanded = false }
        ) {
            majorOptions.forEach {
                DropdownMenuItem(
                    text = { Text(it) },
                    onClick = {
                        major = it
                        majorExpanded = false
                    }
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { yearExpanded = true }
                .padding(12.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
        ) {
            Text(text = year)
        }
        DropdownMenu(
            expanded = yearExpanded,
            onDismissRequest = { yearExpanded = false }
        ) {
            yearOptions.forEach {
                DropdownMenuItem(
                    text = { Text(it) },
                    onClick = {
                        year = it
                        yearExpanded = false
                    }
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        Text("성별", modifier = Modifier.padding(bottom = 8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            listOf("남성", "여성").forEach { option ->
                Button(
                    onClick = { gender = option },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (gender == option) Color.Yellow else Color.LightGray
                    )
                ) {
                    Text(option)
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { addressExpanded = true }
                .padding(12.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
        ) {
            Text(text = address)
        }
        DropdownMenu(
            expanded = addressExpanded,
            onDismissRequest = { addressExpanded = false }
        ) {
            addressOptions.forEach {
                DropdownMenuItem(
                    text = { Text(it) },
                    onClick = {
                        address = it
                        addressExpanded = false
                    }
                )
            }
        }

        Spacer(Modifier.height(32.dp))

        Button(
            onClick = {
                viewModel.updateProfile(
                    ProfileInfo(nickname, major, year, gender, address)
                )
                navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("완료")
        }
    }
}
