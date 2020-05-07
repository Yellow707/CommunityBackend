package com.ipetrov.community.dao

import com.ipetrov.community.database.Database
import io.vertx.sqlclient.Tuple
import java.util.concurrent.TimeUnit

class AccessTokenDao(database: Database): IAccessTokenDao {
    override var database = database

    override fun insertToken(token: String, userID: Int, handler: (id: Int?) -> Unit) {
        val expirationTime = TimeUnit.DAYS.toMillis(1)
        val sql = "INSERT INTO access_tokens (token, user_id, expiration_time) VALUES ($1, $2, $3) RETURNING id"
        database.client.preparedQuery(sql)
                .execute(Tuple.of(token, userID, expirationTime)) { result ->
                    println("Inserted ID: ${result.result().toList().first().getInteger("id")}")
                    handler(result.result().toList().first().getInteger("id"))
                }
    }

    override fun getToken(token: String): String? {
        TODO("Not yet implemented")
    }
}