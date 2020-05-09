package com.ipetrov.community.dao

import com.ipetrov.community.api.model.AuthModel
import com.ipetrov.community.database.Database
import com.ipetrov.community.model.UserModel

interface IUserDao {

    var database: Database

    fun addUser(user: AuthModel, completion: (id: Int) -> Unit)
    fun getUser(authData: AuthModel, completion: (userModel: UserModel) -> Unit)
    fun updateUser()
    fun removeUser()

}