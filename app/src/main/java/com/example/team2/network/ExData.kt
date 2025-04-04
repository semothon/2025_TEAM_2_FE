package com.example.team2.network

import com.example.team2.presentation.chatlist.model.Chat
import com.example.team2.presentation.chatlist.model.ChatStatus
import com.example.team2.presentation.roomdetail.model.Member
import com.example.team2.presentation.roomlist.model.Room

fun exChatData(): List<Chat> {
    return listOf(
        Chat(1, "채팅방 1", "안녕하세요~!", ChatStatus.IN_PROGRESS),
        Chat(2, "채팅방 2", "언제쯤 오시나요?", ChatStatus.IN_PROGRESS),
        Chat(3, "채팅방 3", "조심히 들어가세요~", ChatStatus.COMPLETED)
    )
}