package com.example.team2.presentation.signup

import androidx.lifecycle.ViewModel
import com.example.team2.presentation.signup.model.SignUp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SignUpViewModel : ViewModel() {
    // 상태 값
    private val _form = MutableStateFlow(SignUp("", "", "", "", "", "", "", "", ""))
    val form: StateFlow<SignUp> = _form

    // 이메일 인증 상태
    private val _isEmailVerified = MutableStateFlow(false)
    val isEmailVerified: StateFlow<Boolean> = _isEmailVerified

    // 학교 데이터
    private val _schools = MutableStateFlow(listOf("경희대학교 - 국제", "경희대학교 - 서울"))
    val schools: StateFlow<List<String>> = _schools

    // 이메일 확인
    fun verifyEmail() {
        _isEmailVerified.value = _form.value.email == "test@example.com" // 예시 이메일 체크
    }

    // 학교 선택
    fun selectSchool(school: String) {
        _form.value = _form.value.copy(school = school)
    }

    // 폼 저장
    fun updateForm(newForm: SignUp) {
        _form.value = newForm
    }
}