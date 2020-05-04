package com.ipetrov.community.model

import javax.swing.text.html.ImageView

data class UserModel (
    val id: Int,
    val login: String,
    val email: String,
    val image: ImageView?,
    val password: String
)