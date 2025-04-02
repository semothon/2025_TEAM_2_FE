package com.example.team2.navigation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class NavigationViewModel : ViewModel() {
    private val _isBottom = MutableStateFlow(true)
    val isBottom: StateFlow<Boolean> = _isBottom

    fun bottomEnableTrue() {
        _isBottom.value = true
    }

    fun bottomEnableFalse() {
        _isBottom.value = false
    }
}