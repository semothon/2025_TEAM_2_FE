package com.example.team2.presentation.roomlist

import androidx.lifecycle.ViewModel
import com.example.team2.presentation.roomlist.model.Room
import com.example.team2.network.exRoomData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RoomListViewModel : ViewModel() {
    private val _rooms = MutableStateFlow(emptyList<Room>())
    val rooms: StateFlow<List<Room>> = _rooms

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val _allKeywords = MutableStateFlow(
        listOf("같이 먹을래요", "따로 먹을게요", "동성만", "양식", "분식", "만나서 갈래요?", "비혼연애만 좋겠어요", "한식", "일식")
    )
    val allKeywords: StateFlow<List<String>> = _allKeywords

    private val _selectedKeywords = MutableStateFlow(listOf<String>())
    val selectedKeywords: StateFlow<List<String>> = _selectedKeywords

    init {
        loadRooms()
    }

    private fun loadRooms() {
        _rooms.value = exRoomData()
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
        _rooms.value = _rooms.value.filter {
            it.name.contains(query, ignoreCase = true)
        }
    }

    fun addKeyword(keyword: String) {
        _selectedKeywords.value += keyword
    }

    fun removeKeyword(keyword: String) {
        _selectedKeywords.value = _selectedKeywords.value.filter { it != keyword }
    }

    fun emptyListKeywords() {
        _selectedKeywords.value = emptyList()
    }
}
