package com.ipetrov.community.api.model

import com.google.gson.annotations.SerializedName
import com.ipetrov.community.helpers.Verifiable

class AuthModel(
    val login: String,
    @SerializedName("password")
    val passwordHash: String
) : Verifiable {

    override fun verify() {
        login != ""
        passwordHash != ""
    }
}