package com.example.team2.presentation.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Search
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
import com.example.team2.presentation.component.CustomOutlinedTextField
import com.example.team2.presentation.component.CustomText
import com.example.team2.presentation.component.DropDownMenu
import com.example.team2.presentation.component.RowTextAndIcon
import com.example.team2.presentation.signup.component.SignUpGenderButton
import com.example.team2.presentation.signup.model.Infos
import com.example.team2.presentation.signup.model.UserInfo

@Composable
fun SignUpInfoScreen(viewModel: SignUpViewModel) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var userName by rememberSaveable { mutableStateOf("") }
    var gender by rememberSaveable { mutableStateOf("") }

    val univOptions = viewModel.univOptions.collectAsState()
    val univ = rememberSaveable { mutableStateOf("") }
    val univExpanded = remember { mutableStateOf(false) }

    val departmentOptions by viewModel.departmentOptions.collectAsState()
    val department = rememberSaveable { mutableStateOf("") }
    val departmentExpanded = remember { mutableStateOf(false) }

    val yearOptions = viewModel.yearOptions.collectAsState()
    val year = rememberSaveable { mutableStateOf("") }
    val yearExpanded = remember { mutableStateOf(false) }

    LaunchedEffect(userName, univ, department, year, gender) {
        viewModel.infoSave(UserInfo(userName, univ.value, department.value, year.value, gender))
    }
    LaunchedEffect(univ.value) {
        viewModel.selectedDepartments(univ.value)
    }

    val infos = listOf(
        Infos("학교", univ, "학교를 선택하세요.", univOptions.value, univExpanded, Icons.Default.Search),
        Infos(
            "학과",
            department,
            "학과를 선택하세요.",
            departmentOptions,
            departmentExpanded,
            Icons.Default.KeyboardArrowDown
        ),
        Infos(
            "입학연도",
            year,
            "입학연도를 선택하세요.",
            yearOptions.value,
            yearExpanded,
            Icons.Default.KeyboardArrowDown
        )
    )

    Column {
        Spacer(Modifier.height(20.dp))
        CustomText("이름")
        Spacer(Modifier.height(8.dp))
        CustomOutlinedTextField(
            value = userName,
            placeholder = "이름을 입력하세요.",
            onValueChanged = { userName = it }
        )

        infos.forEach { info ->
            Spacer(Modifier.height(20.dp))
            CustomText(info.text)
            Spacer(Modifier.height(8.dp))
            Column {
                RowTextAndIcon(
                    keyboardController = keyboardController,
                    text = info.selectedText.value,
                    placeholder = info.placeholder,
                    icon = info.icon,
                    onClickExpanded = { info.expanded.value = true }
                )
                DropDownMenu(
                    options = info.options,
                    isExpanded = info.expanded,
                    onClickExpanded = { info.expanded.value = false },
                    onClickOption = { info.selectedText.value = it }
                )
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