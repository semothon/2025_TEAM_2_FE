package com.example.team2.presentation.chatlist.model

data class Chat(
    val id: Int,
    val name: String,
    val lastMessage: String,
    var status: ChatStatus,
    val participantProfileImages: List<Int>,
    val unreadCount: Int
)

enum class ChatStatus {
    IN_PROGRESS,
    COMPLETED
}
