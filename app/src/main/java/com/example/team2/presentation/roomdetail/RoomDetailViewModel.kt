package com.example.team2.presentation.roomdetail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.team2.network.RetrofitClient
import com.example.team2.network.model.SearchQuery
import com.example.team2.presentation.roomdetail.model.Member
import com.example.team2.token
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RoomDetailViewModel : ViewModel() {
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _isButton = MutableStateFlow(true)
    val isButton: StateFlow<Boolean> = _isButton

    private val _members = MutableStateFlow(emptyList<Member>())
    val members: MutableStateFlow<List<Member>> = _members

    fun getRoomDetail(roomId: String, userId:String) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.getRoomDetail(roomId)

                if (response.isSuccessful) {
                    _members.value = response.body()?.memberGroup?.members ?: emptyList()
                    if(_members.value.first().userId == userId)
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

    fun joinRoom(roomId: String) {
        val groupId = SearchQuery(roomId)
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.joinRoom("Bearer $token", groupId)

                if (response.isSuccessful) {
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