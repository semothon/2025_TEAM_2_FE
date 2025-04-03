package com.example.team2.presentation.user

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


@Composable
fun EditProfileScreen(
    viewModel: EditProfileViewModelContract,
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
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alpha = 0.5F
            )
        }


        Text(
            text = "닉네임",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray,
            modifier = Modifier.padding(start = 4.dp, bottom = 4.dp)
        )
        OutlinedTextField(
            value = nickname,
            onValueChange = { nickname = it },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))
        Text(
            text = "학과",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray,
            modifier = Modifier.padding(start = 4.dp, bottom = 4.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
                .clickable { majorExpanded = true }
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = major,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black
            )
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
        Text(
            text = "입학년도",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray,
            modifier = Modifier.padding(start = 4.dp, bottom = 4.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
                .clickable { yearExpanded = true }
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = year,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black
            )
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
        Text(
            text = "주소",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray,
            modifier = Modifier.padding(start = 4.dp, bottom = 4.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
                .clickable { addressExpanded = true }
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = address,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black
            )
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

// ─────────────────────────────────────────────────────────────
// Preview 용 인터페이스 + FakeViewModel + Preview 함수
// ─────────────────────────────────────────────────────────────

interface EditProfileViewModelContract {
    val profileInfo: StateFlow<ProfileInfo>
    val majorOptions: StateFlow<List<String>>
    val yearOptions: StateFlow<List<String>>
    val addressOptions: StateFlow<List<String>>

    fun updateProfile(newProfile: ProfileInfo)
}

// 실제 ViewModel은 이렇게 인터페이스를 구현하게 수정 (변경 전 코드엔 영향 없음)
private class FakeEditProfileViewModel : EditProfileViewModelContract {
    override val profileInfo = MutableStateFlow(
        ProfileInfo(
            nickname = "홍길동",
            major = "컴퓨터공학과",
            year = "22학번",
            gender = "남성",
            address = "정문 앞"
        )
    )

    override val majorOptions = MutableStateFlow(
        listOf("컴퓨터공학과", "전자공학과", "경영학과")
    )

    override val yearOptions = MutableStateFlow(
        listOf("20학번", "21학번", "22학번", "23학번")
    )

    override val addressOptions = MutableStateFlow(
        listOf("정문 앞", "사색의광장 배달존 A", "우정원", "제2기숙사")
    )

    override fun updateProfile(newProfile: ProfileInfo) {
        // 프리뷰용 no-op
    }
}

@Preview(showBackground = true)
@Composable
fun EditProfileScreenPreview() {
    val viewModel = remember { FakeEditProfileViewModel() }
    val navController = rememberNavController()

    EditProfileScreen(
        viewModel = viewModel,
        navController = navController
    )
}
