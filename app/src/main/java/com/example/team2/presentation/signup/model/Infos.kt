package com.example.team2.presentation.signup.model

import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.vector.ImageVector

data class Infos(
    val text: String,
    var selectedText: MutableState<String>,
    val placeholder: String,
    val options: List<String>,
    var expanded: MutableState<Boolean>,
    val icon: ImageVector
)
