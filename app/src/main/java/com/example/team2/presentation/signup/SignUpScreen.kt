package com.example.team2.presentation.signup

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.team2.presentation.component.TopBar
import com.example.team2.ui.theme.Brown2
import com.example.team2.ui.theme.Gray2
import com.example.team2.ui.theme.InnerPadding
import com.example.team2.ui.theme.MainColor

@Composable
fun SignUpScreen(navController: NavController, viewModel: SignUpViewModel = SignUpViewModel()) {
    var selectedTabIndex by remember { mutableIntStateOf(1) }
    val buttonEnable by viewModel.buttonEnableCheck.collectAsState()

    if (selectedTabIndex > 1)
        BackHandler { selectedTabIndex -= 1 }

    Scaffold(
        topBar = {
            TopBar(selectedTabIndex) {
                if (selectedTabIndex > 1) selectedTabIndex -= 1
                else navController.popBackStack()
            }
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(InnerPadding)
        ) {
            when (selectedTabIndex) {
                1 -> SignUpVerificationScreen(viewModel)
                2 -> SignUpInfoScreen(viewModel)
                3 -> SignUpProfileScreen(viewModel)
            }

            Spacer(Modifier.weight(1f))
            Button(
                onClick = {
                    when (selectedTabIndex) {
                        1 -> selectedTabIndex += 1
                        2 -> selectedTabIndex += 1
                        3 -> viewModel.signUp()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .background(if (buttonEnable) MainColor else Gray2, RoundedCornerShape(8.dp)),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                enabled = buttonEnable
            ) {
                Text(
                    text = if (selectedTabIndex == 3) "완료" else "다음",
                    style = TextStyle(color = Brown2)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpPreview() {
    SignUpScreen(rememberNavController())
}