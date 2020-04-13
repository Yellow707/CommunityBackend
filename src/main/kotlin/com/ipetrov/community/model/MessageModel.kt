package com.ipetrov.community.model

import java.util.*

data class MessageModel (
    val id: Int,
    val data: String,
    val date: Date,
    val sender: UserModel
)