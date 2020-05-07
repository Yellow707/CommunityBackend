package com.ipetrov.community.dao

import com.ipetrov.community.api.model.AuthModel
import com.ipetrov.community.database.Database

interface IUserDao {

    var database: Database

    fun addUser(user: AuthModel, handler: (id: Int) -> Unit)
    fun getUser()
    fun updateUser()
    fun removeUser()

}