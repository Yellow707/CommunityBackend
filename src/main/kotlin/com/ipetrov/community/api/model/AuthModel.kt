package com.ipetrov.community.api.model

import com.ipetrov.community.helpers.Verifiable

class AuthModel(
    val login: String,
    val password: String
) : Verifiable {

    override fun verify() {
        login != ""
        password != ""
    }
}