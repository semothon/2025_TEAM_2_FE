package com.example.team2.network

import com.example.team2.R
import com.example.team2.presentation.chatlist.model.Chat
import com.example.team2.presentation.chatlist.model.ChatStatus
import com.example.team2.presentation.roomdetail.model.Member
import com.example.team2.presentation.roomlist.model.Room

fun exChatData(): List<Chat> {
    return listOf(
        Chat(
            id = 1,
            name = "맥도날드",
            lastMessage = "안녕하세요~!",
            status = ChatStatus.IN_PROGRESS,
            participantProfileImages = listOf(
                R.drawable.profile_illustration_1,
                R.drawable.profile_illustration_2
            ),
            unreadCount = 3
        ),
        Chat(
            id = 2,
            name = "찜생찜사",
            lastMessage = "언제쯤 오시나요?",
            status = ChatStatus.IN_PROGRESS,
            participantProfileImages = listOf(
                R.drawable.profile_illustration_1,
                R.drawable.profile_illustration_3
            ),
            unreadCount = 15
        ),
        Chat(
            id = 3,
            name = "미미카츠",
            lastMessage = "오늘 반가웠습니다~^^",
            status = ChatStatus.COMPLETED,
            participantProfileImages = listOf(
                R.drawable.profile_illustration_2,
                R.drawable.profile_illustration_4
            ),
            unreadCount = 2
        )
    )
}

