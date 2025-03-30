package com.example.team2.presentation.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.team2.presentation.component.SignUpCustomText
import com.example.team2.presentation.signup.model.UserInfo
import com.example.team2.ui.theme.Brown1
import com.example.team2.ui.theme.Brown2
import com.example.team2.ui.theme.Gray1
import com.example.team2.ui.theme.Gray4
import com.example.team2.ui.theme.Gray5
import com.example.team2.ui.theme.MainColor
import com.example.team2.ui.theme.MainWhite

@Composable
fun SignUpInfoScreen(viewModel: SignUpViewModel) {
    val univOptions = viewModel.univOptions.collectAsState()
    val yearOptions = viewModel.yearOptions.collectAsState()
    val departmentOptions = viewModel.departmentOptions.collectAsState()
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
        SignUpCustomText("이름")
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
        SignUpCustomText("학교")
        Spacer(Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Brown1.copy(alpha = 0.2f), RoundedCornerShape(8.dp))
                .padding(16.dp)
                .clickable { univExpanded = true },
        ) {
            Text(
                text = univ,
                style = TextStyle(color = if (univ == "학교를 선택하세요.") Gray4 else Brown2),
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

        Spacer(Modifier.height(20.dp))
        SignUpCustomText("학과")
        Spacer(Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Brown1.copy(alpha = 0.2f), RoundedCornerShape(8.dp))
                .padding(16.dp)
                .clickable { departmentExpanded = true },
        ) {
            Text(
                text = department,
                style = TextStyle(color = if (department == "학과를 선택하세요.") Gray4 else Brown2),
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
                    text = { Text(text = option) }
                )
            }
        }

        Spacer(Modifier.height(20.dp))
        SignUpCustomText("입학연도")
        Spacer(Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Brown1.copy(alpha = 0.2f), RoundedCornerShape(8.dp))
                .padding(16.dp)
                .clickable { yearExpanded = true },
        ) {
            Text(
                text = year,
                style = TextStyle(color = if (year == "입학연도를 선택하세요.") Gray4 else Brown2),
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
                    text = { Text(text = option) }
                )
            }
        }

        Spacer(Modifier.height(20.dp))
        SignUpCustomText("성별")
        Spacer(Modifier.height(8.dp))
        Row {
            Button(
                onClick = { gender = "남성" },
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp)
                    .border(
                        if (gender == "남성") 1.dp else 0.dp,
                        if (gender == "남성") MainColor else Color.Transparent,
                        RoundedCornerShape(8.dp)
                    )
                    .background(
                        if (gender == "남성") MainWhite else Gray1,
                        RoundedCornerShape(8.dp)
                    ),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = "남성",
                    style = TextStyle(color = if (gender == "남성") MainColor else Gray5)
                )
            }
            Button(
                onClick = { gender = "여성" },
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp)
                    .border(
                        if (gender == "여성") 1.dp else 0.dp,
                        if (gender == "여성") MainColor else Color.Transparent,
                        RoundedCornerShape(8.dp)
                    )
                    .background(
                        if (gender == "여성") MainWhite else Gray1,
                        RoundedCornerShape(8.dp)
                    ),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = "여성",
                    style = TextStyle(color = if (gender == "여성") MainColor else Gray5)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpInfoPreview() {
    SignUpInfoScreen(SignUpViewModel())
}