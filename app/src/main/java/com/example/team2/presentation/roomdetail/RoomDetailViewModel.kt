package com.example.team2.presentation.roomdetail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.team2.network.RetrofitClient
import com.example.team2.presentation.roomdetail.model.Member
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RoomDetailViewModel : ViewModel() {
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _members = MutableStateFlow(emptyList<Member>())
    val members: MutableStateFlow<List<Member>> = _members

    fun getRoomDetail(roomId: String) {
        viewModelScope.launch {
            val groupId =
            try {
                val response = RetrofitClient.apiService.getRoomDetail(roomId)

                if (response.isSuccessful) {
                    Log.d("testt", response.body().toString())
                    _isLoading.value = true
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