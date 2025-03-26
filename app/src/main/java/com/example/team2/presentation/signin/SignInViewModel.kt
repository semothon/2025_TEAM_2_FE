package com.example.team2.presentation.signin

import androidx.lifecycle.ViewModel
import com.example.team2.presentation.signin.model.SignIn
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SignInViewModel : ViewModel() {
    // 로그인 폼 상태
    private val _form = MutableStateFlow(SignIn("", ""))
    val form: StateFlow<SignIn> = _form

    // 로그인 실패 메시지
    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> = _errorMessage

    // 로그인 처리
    fun login(): Boolean {
        if (_form.value.email == "asdf" && _form.value.password == "asdf") {
            _errorMessage.value = "" // 성공 시 오류 메시지 초기화
            return true
        } else {
            _errorMessage.value = "Invalid email or password" // 실패 시 오류 메시지 설정
            return false
        }
    }

    fun updateForm(newForm: SignIn) {
        _form.value = newForm
    }
}