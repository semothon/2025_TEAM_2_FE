package com.example.team2.presentation.roomlist

import androidx.lifecycle.ViewModel
import com.example.team2.model.Room
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RoomViewModel : ViewModel() {
    private val _rooms = MutableStateFlow(emptyList<Room>())
    val rooms: StateFlow<List<Room>> = _rooms

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    init {
        // 초기 데이터 로딩
        loadRooms()
    }

    private fun loadRooms() {
        _rooms.value = listOf(
            Room("방 제목 1", "상세 설명 1"),
            Room("방 제목 2", "상세 설명 2"),
            Room("방 제목 3", "상세 설명 3"),
            Room("방 제목 4", "상세 설명 4")
        )
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
        // 검색 로직 처리
        _rooms.value = _rooms.value.filter {
            it.name.contains(query, ignoreCase = true)
        }
    }
}
