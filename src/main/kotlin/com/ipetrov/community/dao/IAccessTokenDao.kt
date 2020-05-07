package com.ipetrov.community.dao

import com.ipetrov.community.database.Database

interface IAccessTokenDao {
    var database: Database

    fun insertToken(token: String, userID: Int, handler: (id: Int?) -> Unit)
    fun getToken(token: String): String?
}