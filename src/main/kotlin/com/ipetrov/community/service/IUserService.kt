package com.ipetrov.community.service

import com.ipetrov.community.api.model.AuthModel
import com.ipetrov.community.dao.IUserDao

interface IUserService {
    val userDao: IUserDao

    fun authUser(user: AuthModel)
    fun registerUser(user: AuthModel)
}