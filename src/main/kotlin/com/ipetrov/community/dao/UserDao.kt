package com.ipetrov.community.dao

import com.ipetrov.community.database.Database

class UserDao(database: Database): IUserDao {
    override var database = database

    override fun addUser() {
        TODO("Not yet implemented")
    }

    override fun getUser() {
        TODO("Not yet implemented")
    }

    override fun updateUser() {
        TODO("Not yet implemented")
    }

    override fun removeUser() {
        TODO("Not yet implemented")
    }
}