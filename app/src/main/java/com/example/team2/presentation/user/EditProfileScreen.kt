package com.example.team2.presentation.user

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Search
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
    val scrollState = rememberScrollState()

    val profileInfo by viewModel.profileInfo.collectAsState()

    var nickname by rememberSaveable { mutableStateOf(profileInfo.nickname) }
    var name by rememberSaveable { mutableStateOf(profileInfo.name) }
    var major by rememberSaveable { mutableStateOf(profileInfo.major) }
    var year by rememberSaveable { mutableStateOf(profileInfo.year) }
    var gender by rememberSaveable { mutableStateOf(profileInfo.gender) }
    var address by rememberSaveable { mutableStateOf(profileInfo.address) }
    var school by rememberSaveable { mutableStateOf(profileInfo.school) }

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
            .verticalScroll(scrollState)
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

        NicknameInputField(
            nickname = nickname,
            onNicknameChange = { nickname = it }
        )
        Spacer(Modifier.height(24.dp))

        NameInputField(
            name = name,
            onNameChange = { name = it }
        )
        Spacer(Modifier.height(24.dp))

        SchoolSelectField(
            school = school,
            onClick = {
                // 검색 다이얼로그 띄우거나 이동 처리
            }
        )
        Spacer(modifier = Modifier.height(24.dp))

        SelectField(
            label = "학과",
            selectedOption = major,
            options = majorOptions,
            onOptionSelected = { major = it }
        )
        Spacer(modifier = Modifier.height(24.dp))

        SelectField(
            label = "입학연도",
            selectedOption = year,
            options = yearOptions,
            onOptionSelected = { year = it }
        )
        Spacer(Modifier.height(24.dp))

        GenderSelectField(
            selectedGender = gender,
            onGenderSelected = { gender = it }
        )
        Spacer(Modifier.height(24.dp))

        Spacer(Modifier.height(16.dp))

        AddressInputField(
            address = address,
            onAddressChange = { address = it }
        )


        ConfirmButton(
            text = "완료",
            onClick = {
                viewModel.updateProfile(
                    ProfileInfo(nickname, name, school, major, year, gender, address)
                )
                navController.popBackStack()
            }
        )

    }
}

@Composable
fun NicknameInputField(
    nickname: String,
    onNicknameChange: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "닉네임",
            style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.Medium,
                color = Gray4
            ),
            modifier = Modifier.padding(start = 4.dp, bottom = 4.dp)
        )
        OutlinedTextField(
            value = nickname,
            onValueChange = onNicknameChange,
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MainColor,
                unfocusedBorderColor = Gray5,
                cursorColor = MainColor
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        )
    }
}

@Composable
fun NameInputField(
    name: String,
    onNameChange: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "이름",
            style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.Medium,
                color = Gray4
            ),
            modifier = Modifier.padding(start = 4.dp, bottom = 4.dp)
        )
        OutlinedTextField(
            value = name,
            onValueChange = onNameChange,
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MainColor,
                unfocusedBorderColor = Gray5,
                cursorColor = MainColor
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        )
    }
}

@Composable
fun SchoolSelectField(
    school: String,
    onClick: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "학교",
            style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.Medium,
                color = Gray4
            ),
            modifier = Modifier.padding(start = 4.dp, bottom = 4.dp)
        )

        OutlinedTextField(
            value = school,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "학교 검색",
                    tint = Gray4
                )
            },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .clickable { onClick() },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MainColor,
                unfocusedBorderColor = Gray5,
                disabledBorderColor = Gray5,
                cursorColor = Color.Transparent,
                disabledTextColor = Color.Black
            ),
            enabled = false // 텍스트 수동 입력 막음
        )
    }
}

@Composable
fun SelectField(
    label: String,
    selectedOption: String,
    options: List<String>,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.Medium,
                color = Gray4
            ),
            modifier = Modifier.padding(start = 4.dp, bottom = 4.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .border(1.dp, Gray5, RoundedCornerShape(12.dp))
                .clickable { expanded = true }
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = selectedOption.ifEmpty { "선택해주세요" },
                    style = MaterialTheme.typography.bodyLarge,
                    color = if (selectedOption.isEmpty()) Gray4 else Color.Black
                )
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "드롭다운 화살표",
                    tint = Gray4
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            onOptionSelected(option)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun GenderSelectField(
    selectedGender: String,
    onGenderSelected: (String) -> Unit
) {
    val genderOptions = listOf("남성", "여성")

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "성별",
            style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.Medium,
                color = Gray4
            ),
            modifier = Modifier.padding(start = 4.dp, bottom = 8.dp)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            genderOptions.forEach { gender ->
                val isSelected = gender == selectedGender

                OutlinedButton(
                    onClick = { onGenderSelected(gender) },
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = if (isSelected) MainColor else Color.Transparent,
                        contentColor = if (isSelected) MainWhite else Gray4
                    ),
                    border = BorderStroke(
                        width = 1.dp,
                        color = if (isSelected) MainColor else Gray5
                    ),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp)
                ) {
                    Text(
                        text = gender,
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
                    )
                }
            }
        }
    }
}

@Composable
fun AddressInputField(
    address: String,
    onAddressChange: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "주소",
            style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.Medium,
                color = Gray4
            ),
            modifier = Modifier.padding(start = 4.dp, bottom = 4.dp)
        )
        OutlinedTextField(
            value = address,
            onValueChange = onAddressChange,
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MainColor,
                unfocusedBorderColor = Gray5,
                cursorColor = MainColor
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        )
    }
}

@Composable
fun ConfirmButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MainColor,
            contentColor = MainWhite
        )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.SemiBold
            )
        )
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
            nickname = "apple",
            name = "홍길동",
            major = "컴퓨터공학과",
            year = "22학번",
            gender = "남성",
            address = "정문 앞",
            school = "경희대학교"
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
