package com.ipetrov.community.model

data class ChatModel(
    val id: Int,
    val name: String,
    val participants: Array<UserModel>,
    val messages: Array<MessageModel>
)