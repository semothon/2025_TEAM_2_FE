package com.example.team2.presentation.roomlist

import androidx.lifecycle.ViewModel
import com.example.team2.presentation.roomlist.model.Room
import com.example.team2.network.exRoomData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter

class RoomListViewModel : ViewModel() {

    private val _initialKeywords = MutableStateFlow(
        listOf("같이 먹을래요", "따로 먹을래요", "동성만!", "분식", "일식")
    )
    val initialKeywords: StateFlow<List<String>> = _initialKeywords

    private val _additionalKeywords = MutableStateFlow(
        listOf("디저트", "양식", "아시안", "중식", "고기", "분식", "치킨")
    )
    val additionalKeywords: StateFlow<List<String>> = _additionalKeywords

    private val _rooms = MutableStateFlow(emptyList<Room>())
    private val _filteredRooms = MutableStateFlow(emptyList<Room>())
    val filteredRooms: StateFlow<List<Room>> = _filteredRooms

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val _selectedKeywords = MutableStateFlow(listOf<String>())
    val selectedKeywords: StateFlow<List<String>> = _selectedKeywords

    init {
        loadRooms()
    }

    private fun loadRooms() {
        _rooms.value = exRoomData()
        _filteredRooms.value = _rooms.value
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
        _rooms.value = _rooms.value.filter {
            it.name.contains(query, ignoreCase = true)
        }
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
                _selectedKeywords.value.all { keyword -> room.keywords.contains(keyword) }
            }
    }

    fun emptyListKeywords() {
        _selectedKeywords.value = emptyList()
    }
}
