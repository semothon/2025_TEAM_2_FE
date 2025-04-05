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

    private val _departmentOptions = MutableStateFlow(emptyList<String>())
    val departmentOptions: MutableStateFlow<List<String>> = _departmentOptions

    private val _yearOptions =
        MutableStateFlow(
            (Year.now().value - 10..Year.now().value).sortedDescending().map { it.toString() })
    val yearOptions: MutableStateFlow<List<String>> = _yearOptions

    private val _userInfo = MutableStateFlow(UserInfo("", "", "", "", ""))

    fun buttonEnableFalse() {
        _isButtonEnable.value = false
    }

    fun selectedDepartments(univ: String) {
        when (univ) {
            "경희대학교 국제캠퍼스" -> _departmentOptions.value = listOf(
                "건축공학과",
                "건축학과",
                "골프산업학과",
                "국제학과",
                "기계공학과",
                "디지털콘텐츠학과",
                "도예학과",
                "러시아어학과",
                "물리학과",
                "반도체공학과",
                "사회기반시스템공학과",
                "산업경영공학과",
                "산업디자인학과",
                "생체의공학과",
                "소프트웨어융합학과",
                "스마트팜과학과",
                "스페인어학과",
                "스포츠의학과",
                "스포츠지도학과",
                "시각디자인학과",
                "아시아학과",
                "연극영화학과",
                "우주과학과",
                "응용수학과",
                "응용화학과",
                "의류디자인학과",
                "인공지능학과",
                "유전생명공학과",
                "융합바이오신소재공학과",
                "원자력공학과",
                "원예생명공학과",
                "의류디자인학과",
                "전자공학과",
                "중국어학과",
                "체육학과",
                "컴퓨터공학과",
                "태권도학과",
                "프랑스어학과",
                "포스트모던음악학과",
                "한방생명공학과",
                "한국어학과",
                "환경조경디자인학과",
                "환경학 및 환경공학과",
                "화학공학과"
            )

            "경희대학교 서울캠퍼스" -> _departmentOptions.value = listOf(
                "Hospitality 경영학과",
                "간호학과",
                "경영학과",
                "경제학과",
                "관광.엔터테인먼트학부",
                "국어국문학과",
                "국제통상.금융투자학부(국제통상학전공,국제금융투자학전공)",
                "기악과",
                "글로벌비즈니스전공",
                "글로벌리더전공",
                "무역학과",
                "문화관광산업학과",
                "물리학과",
                "미디어학과",
                "미래정보디스플레이학부",
                "미술학부",
                "발레전공",
                "법학부",
                "빅데이터응용학과",
                "사회학과",
                "산업디자인학과",
                "사학과",
                "성악과",
                "수학과",
                "식품영양학과",
                "생물학과",
                "아동가족학과",
                "약과학과",
                "약학과",
                "영어영문학과",
                "응용영어통번역학과",
                "의예과",
                "의상학과",
                "의학과",
                "조리&푸드디자인학과",
                "조리산업학과",
                "조소전공",
                "주거환경학과",
                "지리학과",
                "철학과",
                "치의예과",
                "치의학과",
                "작곡과",
                "정치외교학과",
                "현대무용전공",
                "한국무용전공",
                "한국화전공",
                "한의예과",
                "한의학과",
                "한약학과",
                "행정학과",
                "회계.세무학과",
                "회화전공",
                "화학과"
            )

            "경희사이버대학교" -> _departmentOptions.value = listOf(
                "AI.빅데이터경영전공",
                "AI사이버보안전공",
                "ICT융합콘텐츠전공",
                "금융전공",
                "글로벌경영학과",
                "글로벌자유학부",
                "기계공학과",
                "노인복지전공",
                "동성만",
                "문화예술경영학과",
                "미국문화영어학과",
                "미디어문예창작학과",
                "미디어영상홍보학과",
                "뷰티.패션산업마케팅전공",
                "산업디자인전공",
                "사회복지전공",
                "상담심리학과",
                "세무회계학과",
                "소방방재전공",
                "스포츠경영전공",
                "스포츠지도전공",
                "실용음악학과",
                "시각미디어디자인전공",
                "아동.보육전공",
                "중국어문화학과",
                "전자정보공학과",
                "청소년.가족전공",
                "후마니타스학과",
                "호텔경영학과",
                "한방건강관리학과",
                "한국어학과",
                "한국어교육전공",
            )
        }
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