package com.ipetrov.community.dao

import com.google.gson.Gson
import com.ipetrov.community.api.model.AuthModel
import com.ipetrov.community.database.Database
import com.ipetrov.community.model.UserModel
import io.vertx.sqlclient.Tuple

class UserDao(database: Database): IUserDao {
    override var database = database

    override fun addUser(user: AuthModel, completion: (id: Int) -> Unit) {
        val sql = "INSERT INTO users (login, password) VALUES ($1, $2) RETURNING ID"
        database.client.preparedQuery(sql)
            .execute(Tuple.of(user.login, user.password)) { result ->
               println("Inserted ID: ${result.result().toList().first().getInteger("id")}")
                completion(result.result().toList().first().getInteger("id"))
            }
    }

    override fun getUser(authData: AuthModel, completion: (userModel: UserModel) -> Unit) {
        val sql = "SELECT * FROM users WHERE LOGIN LIKE ($1)"
        database.client.preparedQuery(sql)
                .execute(Tuple.of(authData.login)) { result ->
                   if (result.succeeded()) {
                       val row = result.result()
                       val user = UserModel(
                               row.first().getInteger("id"),
                               row.first().getString("login"),
                       null,
                       null,
                               row.first().getString("password"))
                       completion(user)
                   }
//                    completion(UserModel())
                }
    }

    override fun updateUser() {
        TODO("Not yet implemented")
    }

    override fun removeUser() {
        TODO("Not yet implemented")
    }
}