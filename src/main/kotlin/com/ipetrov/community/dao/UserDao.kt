package com.ipetrov.community.dao

import com.ipetrov.community.api.model.AuthModel
import com.ipetrov.community.database.Database
import com.ipetrov.community.model.UserModel
import io.vertx.sqlclient.Tuple

class UserDao(database: Database): IUserDao {
    override var database = database

    override fun addUser(user: AuthModel, handler: (id: Int) -> Unit) {
        val sql = "INSERT INTO users (login, password) VALUES ($1, $2) RETURNING ID"
        database.client.preparedQuery(sql)
            .execute(Tuple.of(user.login, user.password)) { result ->
               println("Inserted ID: ${result.result().toList().first().getInteger("id")}")
                handler(result.result().toList().first().getInteger("id"))
            }
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