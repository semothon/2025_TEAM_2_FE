package com.example.team2.presentation.signin

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SignInViewModel : ViewModel() {
    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> = _errorMessage

    fun login(email: String, password: String): Boolean {
        if (email == "asdf" && password == "asdf") {
            _errorMessage.value = ""
            return true
        } else {
            _errorMessage.value = "Invalid email or password"
            return false
        }
    }
}