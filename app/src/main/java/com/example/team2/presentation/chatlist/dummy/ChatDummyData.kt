package com.example.team2.presentation.chatlist.dummy

import com.example.team2.R
import com.example.team2.presentation.chatlist.model.Chat
import com.example.team2.presentation.chatlist.model.ChatStatus

fun exChatData(): List<Chat> {
    return listOf(
        Chat(
            id = 1,
            name = "스터디 그룹",
            lastMessage = "내일 만나요~",
            status = ChatStatus.IN_PROGRESS,
            unreadCount = 4,
            participantProfileImages = listOf(
                R.drawable.profile_illustration_4,
                R.drawable.profile_illustration_2,
                R.drawable.profile_illustration_1
            )
        ),
        Chat(
            id = 2,
            name = "팀 프로젝트",
            lastMessage = "자료 정리했어요!",
            status = ChatStatus.COMPLETED,
            unreadCount = 0,
            participantProfileImages = listOf(
                R.drawable.profile_illustration_1,
                R.drawable.profile_illustration_4,
                R.drawable.profile_illustration_2
            )
        ),
        Chat(
            id = 3,
            name = "디자인 피드백방",
            lastMessage = "Figma 올렸어요",
            status = ChatStatus.IN_PROGRESS,
            unreadCount = 1,
            participantProfileImages = listOf(
                R.drawable.profile_illustration_2,
                R.drawable.profile_illustration_4
            )
        )
    )
}

