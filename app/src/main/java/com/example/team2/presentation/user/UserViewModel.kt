package com.example.team2.presentation.user

import androidx.lifecycle.ViewModel
import com.example.team2.presentation.user.model.ProfileInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import android.util.Log

class EditProfileViewModel : ViewModel() {

    // 드롭다운 항목들
    private val _majorOptions = MutableStateFlow(
        listOf("컴퓨터공학과", "전자공학과", "경영학과")
    )
    val majorOptions: StateFlow<List<String>> = _majorOptions

    private val _yearOptions = MutableStateFlow(
        listOf("20학번", "21학번", "22학번", "23학번")
    )
    val yearOptions: StateFlow<List<String>> = _yearOptions

    private val _addressOptions = MutableStateFlow(
        listOf("사색의광장 배달존 A", "정문 앞", "우정원", "제2기숙사")
    )
    val addressOptions: StateFlow<List<String>> = _addressOptions

    // 사용자 입력 정보를 임시 저장
    private val _profileInfo = MutableStateFlow(
        ProfileInfo("", "", "", "", "")
    )
    val profileInfo: StateFlow<ProfileInfo> = _profileInfo

    fun saveProfile(info: ProfileInfo) {
        _profileInfo.update { info }
        Log.d("EditProfileViewModel", "저장된 프로필: $info")
    }
}
