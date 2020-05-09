package com.ipetrov.community.dao

import com.ipetrov.community.database.Database

interface IAccessTokenDao {
    var database: Database

    fun insertToken(token: String, userID: Int, completion: (id: Int?) -> Unit)
    fun getToken(userID: Int, completion: (token: String?) -> Unit)
}