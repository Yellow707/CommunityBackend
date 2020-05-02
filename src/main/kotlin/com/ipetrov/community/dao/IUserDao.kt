package com.ipetrov.community.dao

import com.ipetrov.community.database.Database

interface IUserDao {

    var database: Database

    fun addUser()
    fun getUser()
    fun updateUser()
    fun removeUser()

}