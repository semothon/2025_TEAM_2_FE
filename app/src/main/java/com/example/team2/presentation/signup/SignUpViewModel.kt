package com.example.team2.presentation.signup

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Search
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.team2.R
import com.example.team2.network.RetrofitClient
import com.example.team2.network.model.ApiResponse
import com.example.team2.network.model.SignUpRequest
import com.example.team2.network.model.VerifyCodeCertRequest
import com.example.team2.network.model.VerifyCodeRequest
import com.example.team2.presentation.signup.model.Infos
import com.example.team2.presentation.signup.model.TextValue
import com.example.team2.presentation.signup.model.UserInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.Year
import kotlin.random.Random

class SignUpViewModel : ViewModel() {
    private val _isButtonEnable = MutableStateFlow(false)
    val isButtonEnable: StateFlow<Boolean> = _isButtonEnable

    private val _signUpVerificationList = MutableStateFlow(
        listOf(
            TextValue("학교 이메일", "이메일을 입력하세요.", "인증번호 받기"),
            TextValue("인증 코드", "인증 코드를 입력하세요.", "확인"),
            TextValue("비밀번호", "비밀번호를 입력하세요.", "중복 확인"),
            TextValue("비밀번호 확인", "비밀번호를 입력하세요.", "확인")
        )
    )
    val signUpVerificationList: MutableStateFlow<List<TextValue>> = _signUpVerificationList

    private val _isCodeVerify = MutableStateFlow(false)
    private val _userEmail = MutableStateFlow("")
    private val _password = MutableStateFlow("")

    fun univVerifyCodeRequest(email: String) {
        val requestBody = VerifyCodeRequest(email)
        Log.d("testt", email)
        RetrofitClient.apiService.verifyCodeRequest(requestBody)
            .enqueue(object : Callback<ApiResponse> {
                override fun onResponse(
                    call: Call<ApiResponse>,
                    response: Response<ApiResponse>
                ) {
                    Log.d("testt", email)
                    if (response.isSuccessful) {
                        // 성공적인 응답 처리
                        response.body()
                        Log.d("testt", response.body().toString())
                    } else {
//                            responseMessage = "Error: ${response.message()}"
                    }
                }

                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
//                        responseMessage = "Request failed: ${t.message}"
                    Log.d("testt", t.message.toString())
                }
            })
    }

    fun savePassword(password: String) {
        if (_isCodeVerify.value) {
            _password.value = password
            _isButtonEnable.value = true
        }
    }

    fun univVerifyCodeCertRequest(email: String, verificationCode: String) {
        val requestBody = VerifyCodeCertRequest(email, verificationCode)
        viewModelScope.launch {
            RetrofitClient.apiService.verifyCodeCertRequest(requestBody)
                .enqueue(object : Callback<ApiResponse> {
                    override fun onResponse(
                        call: Call<ApiResponse>,
                        response: Response<ApiResponse>
                    ) {
                        if (response.isSuccessful) {
//                        responseMessage.value = response.body()?.message ?: "Success"
                            _isCodeVerify.value = true
                            _userEmail.value = email
                        } else {
//                        responseMessage.value = "Error: ${response.message()}"
                        }
                    }

                    override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
//                    responseMessage.value = "Request failed: ${t.message}"
                    }
                })
        }
    }

    private val _univOptions = MutableStateFlow(listOf("경희대학교 국제캠퍼스", "경희대학교 서울캠퍼스", "경희사이버대학교"))
    val univOptions: MutableStateFlow<List<String>> = _univOptions

    private val _departmentOptions = MutableStateFlow(listOf("소프트웨어융합학과", "컴퓨터공학과", "인공지능학과"))
    val departmentOptions: MutableStateFlow<List<String>> = _departmentOptions

    private val _yearOptions =
        MutableStateFlow(
            (Year.now().value - 10..Year.now().value).sortedDescending().map { it.toString() })
    val yearOptions: MutableStateFlow<List<String>> = _yearOptions

    private val _userInfo = MutableStateFlow(UserInfo("", "", "", "", ""))

    fun buttonEnableFalse() {
        _isButtonEnable.value = false
    }

    fun infoSave(userInfo: UserInfo) {
        if (userInfo.name.isNotEmpty()
            && userInfo.univ.isNotEmpty()
            && userInfo.department.isNotEmpty()
            && userInfo.year.isNotEmpty()
            && userInfo.gender.isNotEmpty()
        ) {
            _userInfo.value = UserInfo(
                userInfo.name,
                userInfo.univ,
                userInfo.department,
                userInfo.year,
                if (userInfo.gender == "남성") "M" else "W"
            )
            _isButtonEnable.value = true
        } else {
            _isButtonEnable.value = false
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

    fun refreshRandomProfileIllustration() {
        _randomProfileIllustration.value = imageResources[Random.nextInt(imageResources.size)]
    }

    fun duplicationCheck(nickName: String) {
        _nickName.value = nickName
        // 중복이 아니면
        if (nickName.isNotEmpty()) _isButtonEnable.value = true
        else _isButtonEnable.value = false
    }

    fun signUp() {
        val requestBody = SignUpRequest(
            name = _userInfo.value.name,
            password = _password.value,
            year = _userInfo.value.year.takeLast(2),
            department = _userInfo.value.department,
            gender = _userInfo.value.gender,
            nickName = _nickName.value,
            illustration = _randomProfileIllustration.value,
            email = _userEmail.value
        )
        RetrofitClient.apiService.signUpRequest(requestBody)
            .enqueue(object : Callback<ApiResponse> {
                override fun onResponse(
                    call: Call<ApiResponse>,
                    response: Response<ApiResponse>
                ) {
                    if (response.isSuccessful) {
//                            responseMessage.value =
//                                response.body()?.message ?: "Registration Successful"
                    } else {
//                            responseMessage.value = "Error: ${response.message()}"
                    }
                }

                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
//                        responseMessage.value = "Request failed: ${t.message}"
                }
            })
    }
}