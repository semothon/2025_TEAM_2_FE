package com.example.team2.presentation.user

import androidx.lifecycle.ViewModel
import com.example.team2.presentation.user.model.ProfileInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class EditProfileViewModel : ViewModel(), EditProfileViewModelContract {

    private val _profileInfo = MutableStateFlow(
        ProfileInfo(
            nickname = "닉네임2",
            name = "이름1",
            school = "경희대학교",
            major = "컴퓨터공학과",
            year = "21학번",
            gender = "남",
            address = "사색의광장 배달존 A"
        )
    )
    override val profileInfo: StateFlow<ProfileInfo> = _profileInfo

    override fun updateProfile(info: ProfileInfo) {
        _profileInfo.value = info
    }

    // 드롭다운 항목들
    private val _majorOptions = MutableStateFlow(
        listOf("컴퓨터공학과", "전자공학과", "경영학과")
    )
    override val majorOptions: StateFlow<List<String>> = _majorOptions

    private val _yearOptions = MutableStateFlow(
        listOf("20학번", "21학번", "22학번", "23학번")
    )
    override val yearOptions: StateFlow<List<String>> = _yearOptions

    private val _addressOptions = MutableStateFlow(
        listOf("사색의광장 배달존 A", "정문 앞", "우정원", "제2기숙사")
    )
    override val addressOptions: StateFlow<List<String>> = _addressOptions
}
