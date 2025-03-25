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
}
