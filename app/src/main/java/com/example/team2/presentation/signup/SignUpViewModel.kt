package com.example.team2.presentation.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.team2.presentation.signup.model.SignUp
import com.example.team2.presentation.signup.model.TextValue
import com.example.team2.presentation.signup.model.UserInfo
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.Year

class SignUpViewModel : ViewModel() {
    private val _buttonEnableCheck = MutableStateFlow(false)
    val buttonEnableCheck: StateFlow<Boolean> = _buttonEnableCheck

    private val _signUpVerificationList = MutableStateFlow(
        listOf(
            TextValue("학교 이메일", "이메일을 입력하세요.", "인증번호 받기"),
            TextValue("인증 코드", "인증 코드를 입력하세요.", "확인"),
            TextValue("비밀번호", "비밀번호를 입력하세요.", "중복 확인"),
            TextValue("비밀번호 확인", "비밀번호를 입력하세요.", "확인")
        )
    )
    val signUpVerificationList: MutableStateFlow<List<TextValue>> = _signUpVerificationList

    private suspend fun performNetworkCheck(info: SignUp): Boolean {
        delay(100)
        return true
    }

    fun verification(user: SignUp) {
        viewModelScope.launch {
            if (user.email.isNotEmpty()
                && user.verificationCode.isNotEmpty()
                && user.password.isNotEmpty()
                && user.confirmPassword.isNotEmpty()
            )
                try {
                    if (performNetworkCheck(user)) {
                        _buttonEnableCheck.value = true
                    } else {
                        _buttonEnableCheck.value = false
                    }
                } catch (e: Exception) {
                    _buttonEnableCheck.value = false
                }
            else
                _buttonEnableCheck.value = false
        }
    }

    private val _univOptions = MutableStateFlow(listOf("경희대학교 국제캠퍼스", "경희대학교 서울캠퍼스", "경희사이버대학교"))
    val univOptions: MutableStateFlow<List<String>> = _univOptions

    private val _departmentOptions = MutableStateFlow(listOf("소프트웨어융합학과", "컴퓨터공학과", "인공지능학과"))
    val departmentOptions: MutableStateFlow<List<String>> = _departmentOptions

    private val _yearOptions = MutableStateFlow((2010..Year.now().value).map { it.toString() })
    val yearOptions: MutableStateFlow<List<String>> = _yearOptions

    fun infoSave(userInfo: UserInfo) {
        viewModelScope.launch {
            if (userInfo.name.isNotEmpty()
                && userInfo.univ.isNotEmpty()
                && userInfo.department.isNotEmpty()
                && userInfo.year.isNotEmpty()
                && userInfo.gender.isNotEmpty()
            )
                _buttonEnableCheck.value = true
            else
                _buttonEnableCheck.value = false
        }
    }

    fun duplicationCheck(nickName: String) {

    }

    fun signUp() {

    }
}