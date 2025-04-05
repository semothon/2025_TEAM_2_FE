package com.example.team2.presentation.participationlist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.team2.network.RetrofitClient
import com.example.team2.network.model.UpdateRoomRequest
import com.example.team2.presentation.participationlist.model.ParticipationRoom
import com.example.team2.token
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ParticipationListViewModel : ViewModel() {
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _roomDeals = MutableStateFlow(emptyList<ParticipationRoom>())
    private val _filteredRoomDeals = MutableStateFlow(emptyList<ParticipationRoom>())
    val filteredRoomDeals: StateFlow<List<ParticipationRoom>> = _filteredRoomDeals

    fun getRoomDeals() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.getRoomDeals("Bearer $token")

                if (response.isSuccessful) {
                    _roomDeals.value = response.body()?.roomDerail ?: emptyList()
                    _filteredRoomDeals.value = _roomDeals.value
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

    fun filterList(option: String) {
        when (option) {
            "완료" -> _filteredRoomDeals.value =
                _roomDeals.value.filter { it.roomStatus == 2 }.ifEmpty { emptyList() }

            "진행 중" -> _filteredRoomDeals.value =
                _roomDeals.value.filter { it.roomStatus == 0 || it.roomStatus == 1 }
                    .ifEmpty { emptyList() }

            else -> _filteredRoomDeals.value = _roomDeals.value
        }
    }

    fun updateRoom(roomId: String) {
        val updateRoom = UpdateRoomRequest(roomId, 2)
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.updateRoom("Bearer $token", updateRoom)
                if (response.isSuccessful) {
                    getRoomDeals()
                    Log.d("testt", response.body().toString())
                } else {
                    // 요청 실패 처리
                    Log.d("API Error", "Request failed with status: ${response.code()}")
                }
            } catch (e: Exception) {
                // 예외 처리
                Log.e("API Error", "Request failed: ${e.message}")
            }
        }
    }

    fun isLoadingFalse() {
        _isLoading.value = false
    }
}
