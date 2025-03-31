package com.example.team2.presentation.signup

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.team2.presentation.component.CustomText
import com.example.team2.presentation.signup.component.SignUpGenderButton
import com.example.team2.presentation.signup.model.UserInfo
import com.example.team2.ui.theme.Brown1
import com.example.team2.ui.theme.Brown2
import com.example.team2.ui.theme.Gray3
import com.example.team2.ui.theme.Gray4

@Composable
fun SignUpInfoScreen(viewModel: SignUpViewModel) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val univOptions = viewModel.univOptions.collectAsState()
    val departmentOptions = viewModel.departmentOptions.collectAsState()
    val yearOptions = viewModel.yearOptions.collectAsState()
    var userName by rememberSaveable { mutableStateOf("") }
    var univ by rememberSaveable { mutableStateOf("학교를 선택하세요.") }
    var department by rememberSaveable { mutableStateOf("학과를 선택하세요.") }
    var year by rememberSaveable { mutableStateOf("입학연도를 선택하세요.") }
    var gender by rememberSaveable { mutableStateOf("") }
    var univExpanded by remember { mutableStateOf(false) }
    var departmentExpanded by remember { mutableStateOf(false) }
    var yearExpanded by remember { mutableStateOf(false) }

    LaunchedEffect(userName, univ, department, year, gender) {
        viewModel.infoSave(UserInfo(userName, univ, department, year, gender))
    }

    Column {
        Spacer(Modifier.height(20.dp))
        CustomText("이름")
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = userName,
            onValueChange = { userName = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = "이름을 입력하세요.",
                    style = TextStyle(color = Gray4)
                )
            },
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Brown1.copy(alpha = 0.2f),
                unfocusedBorderColor = Brown1.copy(alpha = 0.2f),
            ),
            maxLines = 1
        )

        Spacer(Modifier.height(20.dp))
        CustomText("학교")
        Spacer(Modifier.height(8.dp))
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Brown1.copy(alpha = 0.2f), RoundedCornerShape(8.dp))
                    .padding(16.dp)
                    .clickable {
                        keyboardController?.hide()
                        univExpanded = true
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = univ,
                    style = TextStyle(color = if (univ == "학교를 선택하세요.") Gray4 else Brown2),
                )
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = Gray3
                )
            }
            DropdownMenu(
                expanded = univExpanded,
                onDismissRequest = { univExpanded = false }
            ) {
                univOptions.value.forEach { option ->
                    DropdownMenuItem(
                        onClick = {
                            univ = option
                            univExpanded = false
                        },
                        text = { Text(text = option) }
                    )
                }
            }
        }

        Spacer(Modifier.height(20.dp))
        CustomText("학과")
        Spacer(Modifier.height(8.dp))
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Brown1.copy(alpha = 0.2f), RoundedCornerShape(8.dp))
                    .padding(16.dp)
                    .clickable { departmentExpanded = true },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = department,
                    style = TextStyle(color = if (department == "학과를 선택하세요.") Gray4 else Brown2),
                )
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "KeyboardArrowDown",
                    tint = Gray3
                )
            }
            DropdownMenu(
                expanded = departmentExpanded,
                onDismissRequest = { departmentExpanded = false }
            ) {
                departmentOptions.value.forEach { option ->
                    DropdownMenuItem(
                        onClick = {
                            department = option
                            departmentExpanded = false
                        },
                        text = { Text(text = option) },
                    )
                }
            }
        }

        Spacer(Modifier.height(20.dp))
        CustomText("입학연도")
        Spacer(Modifier.height(8.dp))
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Brown1.copy(alpha = 0.2f), RoundedCornerShape(8.dp))
                    .padding(16.dp)
                    .clickable { departmentExpanded = true },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = year,
                    style = TextStyle(color = if (year == "입학연도를 선택하세요.") Gray4 else Brown2),
                )
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "KeyboardArrowDown",
                    tint = Gray3
                )
            }
            DropdownMenu(
                expanded = yearExpanded,
                onDismissRequest = { yearExpanded = false }
            ) {
                yearOptions.value.forEach { option ->
                    DropdownMenuItem(
                        onClick = {
                            year = option
                            yearExpanded = false
                        },
                        text = { Text(text = option) },
                    )
                }
            }
        }

        Spacer(Modifier.height(20.dp))
        CustomText("성별")
        Spacer(Modifier.height(8.dp))
        Row {
            SignUpGenderButton(gender, "남성", Modifier.weight(1f)) { gender = "남성" }
            SignUpGenderButton(gender, "여성", Modifier.weight(1f)) { gender = "여성" }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpInfoPreview() {
    SignUpInfoScreen(SignUpViewModel())
}