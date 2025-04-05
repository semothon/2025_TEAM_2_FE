package com.example.team2.presentation.participationdetail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.team2.network.RetrofitClient
import com.example.team2.network.model.BlockUserIdRequest
import com.example.team2.presentation.roomdetail.model.Member
import com.example.team2.presentation.roomdetail.model.User
import com.example.team2.token
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ParticipationDetailViewModel : ViewModel() {
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _isButton = MutableStateFlow(true)
    val isButton: StateFlow<Boolean> = _isButton

    private val _members = MutableStateFlow(emptyList<Member>())
    val members: MutableStateFlow<List<Member>> = _members

    private val _location = MutableStateFlow("")
    val location: StateFlow<String> = _location

    private val _popBack = MutableStateFlow(false)
    val popBack: MutableStateFlow<Boolean> = _popBack

    private val _isDialog = MutableStateFlow(false)
    val isDialog: StateFlow<Boolean> = _isDialog

    private val _member = MutableStateFlow(
        User(
            userId = "",
            illustration = 0,
            nickName = "",
            userName = "",
            univInfo = listOf(""),
            gender = "",
            favoriteCount = 0
        )
    )
    val member: StateFlow<User> = _member

    private val _roomOptions = MutableStateFlow(listOf("모집 중", "모집 완료", "거래 완료"))
    val roomOptions: MutableStateFlow<List<String>> = _roomOptions

    fun getRoomDetail(roomId: String, userId: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.getRoomDetail(roomId)

                if (response.isSuccessful) {
                    _members.value = response.body()?.memberGroup?.members ?: emptyList()
                    _location.value = response.body()?.memberGroup?.location ?: ""
                    if (_members.value.first().userId == userId)
                        _isButton.value = false
                    _isLoading.value = true
                    Log.d("testt", response.body().toString())
                } else {
                    // 실패 시 처리
                    Log.d("testt", "Error: ${response.message()}")
                }
            } catch (e: Exception) {
                // 요청 실패 시 처리
                Log.d("testt", "Request failed: ${e.message}")
            }
        }
    }

    fun getUserDetail(userId: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.getUserDetail(userId)

                if (response.isSuccessful) {
                    _member.value = response.body()?.userDetailList!!
                    _isDialog.value = true
                    Log.d("testt", response.body().toString())
                } else {
                    // 실패 시 처리
                    Log.d("testt", "Error: ${response.message()}")
                }
            } catch (e: Exception) {
                // 요청 실패 시 처리
                Log.d("testt", "Request failed: ${e.message}")
            }
        }
    }

    fun isDialogFalse() {
        _isDialog.value = false
    }

    fun blockUser(userId: String) {
        val userId = BlockUserIdRequest(userId)
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.blockUser("Bearer $token", userId)

                if (response.isSuccessful) {
                    _popBack.value = true
                    Log.d("testt", response.body().toString())
                } else {
                    // 실패 시 처리
                    Log.d("testt", "Error: ${response.message()}")
                }
            } catch (e: Exception) {
                // 요청 실패 시 처리
                Log.d("testt", "Request failed: ${e.message}")
            }
        }
    }
}