package com.ipetrov.community.service

import com.ipetrov.community.api.model.AuthModel
import com.ipetrov.community.dao.IAccessTokenDao
import com.ipetrov.community.dao.IUserDao

interface IUserService {
    val userDao: IUserDao
    val accessTokenDao: IAccessTokenDao

    fun authUser(user: AuthModel, complete: (token: String?) -> Unit)
    fun registerUser(user: AuthModel, complete: (token: String?) -> Unit)
}