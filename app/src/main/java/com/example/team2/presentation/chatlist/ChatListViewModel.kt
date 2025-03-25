package com.example.team2.presentation.chatlist

import androidx.lifecycle.ViewModel
import com.example.team2.network.exChatData
import com.example.team2.presentation.chatlist.model.Chat
import com.example.team2.presentation.chatlist.model.ChatStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ChatListViewModel : ViewModel() {
    private val _chatRooms = MutableStateFlow<List<Chat>>(emptyList())
    val chatRooms: StateFlow<List<Chat>> = _chatRooms

    init {
        loadChats()
    }

    private fun loadChats(){
        _chatRooms.value = exChatData()
    }

    fun filterChatRooms(status: ChatStatus?): List<Chat> {
        return _chatRooms.value.filter { it.status == status }
    }
}
