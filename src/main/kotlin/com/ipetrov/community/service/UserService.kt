package com.ipetrov.community.service

import com.ipetrov.community.api.model.AuthModel
import com.ipetrov.community.dao.IUserDao

class UserService(userDao: IUserDao): IUserService {
    override val userDao = userDao

    override fun authUser(user: AuthModel) {
        TODO("Not yet implemented")
    }

    override fun registerUser(user: AuthModel) {
        userDao.addUser(user)
    }
}