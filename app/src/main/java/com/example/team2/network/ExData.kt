package com.example.team2.network

import com.example.team2.presentation.chatlist.model.Chat
import com.example.team2.presentation.chatlist.model.ChatStatus
import com.example.team2.presentation.roomlist.model.Room

fun exRoomData(): List<Room> {
    return listOf(
        Room("방 제목 1", "상세 설명 1"),
        Room("방 제목 2", "상세 설명 2"),
        Room("방 제목 3", "상세 설명 3"),
        Room("방 제목 4", "상세 설명 4")
    )
}

fun exChatData(): List<Chat> {
    return listOf(
        Chat(1, "채팅방 1", "안녕하세요~!", ChatStatus.IN_PROGRESS),
        Chat(2, "채팅방 2", "언제쯤 오시나요?", ChatStatus.IN_PROGRESS),
        Chat(3, "채팅방 3", "조심히 들어가세요~", ChatStatus.COMPLETED)
    )
}