package com.example.team2.presentation.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.team2.R
import com.example.team2.presentation.signup.model.SignUp
import com.example.team2.presentation.signup.model.TextValue
import com.example.team2.presentation.signup.model.UserInfo
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.Year
import kotlin.random.Random

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
        // 학교 학생 확인 되면
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

    private var _userInfo = MutableStateFlow(UserInfo("", "", "", "", ""))
    val userInfo: MutableStateFlow<UserInfo> = _userInfo

    fun infoSave(userInfo: UserInfo) {
        viewModelScope.launch {
            if (userInfo.name.isNotEmpty()
                && userInfo.univ.isNotEmpty()
                && userInfo.department.isNotEmpty()
                && userInfo.year.isNotEmpty()
                && userInfo.gender.isNotEmpty()
            ) {
                // 중복이 아니면
                _buttonEnableCheck.value = true
                _userInfo.value = userInfo
            } else
                _buttonEnableCheck.value = false
        }
    }

    private val imageResources = listOf(
        R.drawable.profile_illustration_1,
        R.drawable.profile_illustration_2,
        R.drawable.profile_illustration_3,
        R.drawable.profile_illustration_4,
        R.drawable.profile_illustration_5,
        R.drawable.profile_illustration_6
    )

    private val _randomProfileIllustration =
        MutableStateFlow(imageResources[Random.nextInt(imageResources.size)])
    val randomProfileIllustration: StateFlow<Int> = _randomProfileIllustration

    private var _nickName = MutableStateFlow("")
    val nickName: StateFlow<String> = _nickName

    fun buttonEnableFalse() {
        _buttonEnableCheck.value = false
    }

    fun refreshRandomProfileIllustration() {
        _randomProfileIllustration.value = imageResources[Random.nextInt(imageResources.size)]
    }

    fun duplicationCheck(nickName: String) {
        _nickName.value = nickName
        // 중복이 아니면
        if (nickName.isNotEmpty()) _buttonEnableCheck.value = true
        else _buttonEnableCheck.value = false
    }

    fun signUp(userInfo: UserInfo, profileIllustration: Int, nickName: String) {
        // 회원가입 성공
    }
}