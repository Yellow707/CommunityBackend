package com.ipetrov.community.service

import com.ipetrov.community.api.model.AuthModel
import com.ipetrov.community.dao.IAccessTokenDao
import com.ipetrov.community.dao.IUserDao
import com.ipetrov.community.helpers.TokenGenerator
import com.sun.org.apache.xpath.internal.operations.Bool

class UserService(userDao: IUserDao, accessTokenDao: IAccessTokenDao): IUserService {
    override val userDao = userDao
    override val accessTokenDao = accessTokenDao

    override fun authUser(user: AuthModel) {
        TODO("Not yet implemented")
    }

    override fun registerUser(user: AuthModel, complete: (token: String?) -> Unit) {
        userDao.addUser(user) { userId ->
            val accessToken = TokenGenerator.generate()
            accessTokenDao.insertToken(accessToken, userId) { tokenID ->
                if (tokenID != null) {
                    complete(accessToken)
                } else {
                    complete(null)
                }
            }
        }
    }
}