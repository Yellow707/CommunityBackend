package com.ipetrov.community.database

import io.vertx.core.AbstractVerticle
import io.vertx.core.Vertx
import io.vertx.kotlin.pgclient.pgConnectOptionsOf
import io.vertx.kotlin.sqlclient.poolOptionsOf
import io.vertx.pgclient.PgPool

class Database(vertx: Vertx) {

    var client: PgPool
        private set

    var connectOptions = pgConnectOptionsOf(
        port = 5433,
        host = "localhost",
        database = "postgres",
        user = "postgres",
        password = "123321")

    var poolOptions = poolOptionsOf(
        maxSize = 5
    )

    init {
        client = PgPool.pool(vertx, connectOptions, poolOptions)
    }
}