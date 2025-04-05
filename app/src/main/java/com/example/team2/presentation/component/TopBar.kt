package com.example.team2.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.team2.ui.theme.MainWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(tobBarText: String, boolean: Boolean = true, onClick: () -> Unit) {
    TopAppBar(
        title = {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = tobBarText,
                    modifier = Modifier.align(Alignment.Center)
                )
                if (boolean)
                    IconButton(
                        onClick = { onClick() },
                        modifier = Modifier.align(Alignment.CenterStart)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "뒤로 가기"
                        )
                    }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MainWhite
        )
    )
}