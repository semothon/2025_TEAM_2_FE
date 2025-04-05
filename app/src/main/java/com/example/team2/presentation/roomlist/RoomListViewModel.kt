package com.example.team2.presentation.roomlist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.team2.network.RetrofitClient
import com.example.team2.presentation.roomlist.model.Room
import com.example.team2.token
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RoomListViewModel : ViewModel() {
    private val _initialKeywords = MutableStateFlow(
        listOf("같이 먹을래요", "따로 먹을래요", "동성만!", "분식", "일식")
    )
    val initialKeywords: StateFlow<List<String>> = _initialKeywords

    private val _additionalKeywords = MutableStateFlow(
        listOf("패스트푸드", "디저트", "양식", "아시안", "중식", "고기", "치킨")
    )
    val additionalKeywords: StateFlow<List<String>> = _additionalKeywords

    private val _rooms = MutableStateFlow(emptyList<Room>())
    private val _filteredRooms = MutableStateFlow(emptyList<Room>())
    val filteredRooms: StateFlow<List<Room>> = _filteredRooms

    private val _selectedKeywords = MutableStateFlow(listOf<String>())
    val selectedKeywords: StateFlow<List<String>> = _selectedKeywords

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun fetchRoomListData() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.getRoomListData("Bearer $token")
                if (response.isSuccessful) {
                    _rooms.value = response.body()?.roomList ?: emptyList()
                    _filteredRooms.value = _rooms.value
                    _isLoading.value = true
                    Log.d("testt", response.body().toString())
                } else {
                    Log.d("testt", response.message())
//                     _errorMessage.value = "Error: ${response.message()}"
                }
            } catch (e: Exception) {
                Log.d("testt", e.message.toString())
//                 _errorMessage.value = "Request failed: ${e.message}"
            }
        }
    }

    fun onSearchQueryChanged(query: String) {
        _isLoading.value = false
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.getSearchedRoomList("Bearer $token", query)
                if (response.isSuccessful) {
                    _filteredRooms.value = response.body()?.roomList ?: emptyList()
                    _isLoading.value = true
                    Log.d("testt", response.body().toString())
                } else {
                    Log.d("testt", response.message())
//                     _errorMessage.value = "Error: ${response.message()}"
                }
            } catch (e: Exception) {
                Log.d("testt", e.message.toString())
//                 _errorMessage.value = "Request failed: ${e.message}"
            }
        }
//        _rooms.value = _rooms.value.filter {
//            it.name.contains(query, ignoreCase = true)
//        }
    }

    fun editKeyword(keyword: String) {
        if (_selectedKeywords.value.contains(keyword))
            _selectedKeywords.value = _selectedKeywords.value.filter { it != keyword }
        else
            _selectedKeywords.value += keyword
        filterRooms()
    }

    private fun filterRooms() {
        if (_selectedKeywords.value.isEmpty())
            _filteredRooms.value = _rooms.value
        else
            _filteredRooms.value = _rooms.value.filter { room ->
                _selectedKeywords.value.all { keyword -> room.tagChips.contains(keyword) }
            }
    }

    fun emptyListKeywords() {
        _selectedKeywords.value = emptyList()
    }
}
