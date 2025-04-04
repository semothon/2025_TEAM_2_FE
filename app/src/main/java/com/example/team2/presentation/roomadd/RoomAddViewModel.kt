package com.example.team2.presentation.roomadd

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.team2.network.RetrofitClient
import com.example.team2.presentation.roomadd.model.RoomDetail
import com.example.team2.token
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class RoomAddViewModel : ViewModel() {
    private val _foodOptions = MutableStateFlow(listOf("한식", "중식", "양식", "일식", "분식"))
    val foodOptions: MutableStateFlow<List<String>> = _foodOptions

    private val _memberCountOptions = MutableStateFlow((2..10).map { it.toString() })
    val memberCountOptions: MutableStateFlow<List<String>> = _memberCountOptions

    fun makeRoom(newRoom: RoomDetail) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.makeRoom("Bearer $token", newRoom)

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