package com.example.team2.network

import com.example.team2.presentation.chatlist.model.Chat
import com.example.team2.presentation.chatlist.model.ChatStatus
import com.example.team2.presentation.roomdetail.model.Member
import com.example.team2.presentation.roomlist.model.Room

fun exRoomData(): List<Room> {
    return listOf(
        Room("맥도날드", "정건 맥날 같이 시커드실 분 구합니다.", listOf("따로 먹을래요", "패스트푸드", "4인팟"), 4),
        Room("미미카츠", "기숙사 1층에서 같이 드실 분!", listOf("같이 먹을래요", "동성만!", "3인팟"), 3),
        Room("포케올데이", "포케 같이 시켜먹으실 분", listOf("따로 먹을래요", "샐러드", "5인팟"), 5)
    )
}

fun exChatData(): List<Chat> {
    return listOf(
        Chat(1, "채팅방 1", "안녕하세요~!", ChatStatus.IN_PROGRESS),
        Chat(2, "채팅방 2", "언제쯤 오시나요?", ChatStatus.IN_PROGRESS),
        Chat(3, "채팅방 3", "조심히 들어가세요~", ChatStatus.COMPLETED)
    )
}

fun loadMembers(): List<Member> {
    return listOf(
        Member("APPLE", "시각디자인학과", "2020", 10000, 100),
        Member("도라에몽", "컴퓨터공학과", "2022", 3000, 153),
        Member("크리넥스", "산업경영공학과", "2024", 20000, 201)
    )
}