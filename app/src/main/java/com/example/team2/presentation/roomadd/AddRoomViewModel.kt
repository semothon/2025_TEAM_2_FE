package com.example.team2.presentation.roomadd

import androidx.lifecycle.ViewModel
import com.example.team2.presentation.roomadd.model.RoomDetail
import kotlinx.coroutines.flow.MutableStateFlow

class RoomAddViewModel : ViewModel() {
    private val _foodOptions = MutableStateFlow(listOf("한식", "중식", "양식", "일식", "분식"))
    val foodOptions: MutableStateFlow<List<String>> = _foodOptions

    private val _memberCountOptions = MutableStateFlow((2..10).map { it.toString() })
    val memberCountOptions: MutableStateFlow<List<String>> = _memberCountOptions

    fun makeRoom(newRoom: AddRoom) {
        // 방 추가 로직
    }
}