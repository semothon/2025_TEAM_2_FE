package com.example.team2.presentation.participationlist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.team2.network.RetrofitClient
import com.example.team2.presentation.participationlist.model.ParticipationRoom
import com.example.team2.token
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ParticipationListViewModel : ViewModel() {
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _roomDeals = MutableStateFlow(emptyList<ParticipationRoom>())
    val roomDeals: MutableStateFlow<List<ParticipationRoom>> = _roomDeals

    fun getRoomDeals() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.getRoomDeals("Bearer $token")

                if (response.isSuccessful) {
                    _roomDeals.value = response.body()?.roomDerail ?: emptyList()
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
}