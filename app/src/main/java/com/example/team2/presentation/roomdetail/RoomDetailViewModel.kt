package com.example.team2.presentation.roomdetail

import androidx.lifecycle.ViewModel
import com.example.team2.network.loadMembers
import com.example.team2.presentation.roomdetail.model.Member
import kotlinx.coroutines.flow.MutableStateFlow

class RoomDetailViewModel : ViewModel() {
    private val _members = MutableStateFlow(emptyList<Member>())
    val members: MutableStateFlow<List<Member>> = _members

    init {
        _members.value = loadMembers()
    }


}