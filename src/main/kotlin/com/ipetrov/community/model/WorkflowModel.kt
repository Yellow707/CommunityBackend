package com.ipetrov.community.model

data class WorkflowModel(
    val id: Int,
    val name:String,
    val description: String?,
    val users: Array<UserModel>,
    val creator: UserModel,
    val chats: Array<ChatModel>
)