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
        createTablesIfNotExists()
    }

    fun createTablesIfNotExists() {
        client
            .query("CREATE TABLE IF NOT EXISTS workspaces (" +
                    "${DatabaseConst.ID} SERIAL NOT NULL," +
                    "${DatabaseConst.TITLE} TEXT NOT NULL," +
                    "${DatabaseConst.DESCRIPTION} TEXT NOT NULL," +
                    "${DatabaseConst.CREATORID} INT NOT NULL," +
                    "PRIMARY KEY (${DatabaseConst.ID})" +
                    ")").execute { result ->
                if (result.succeeded()) {
                    println("Workspace table created")
                } else {
                    println("Workspace table not created: $result")
                }
            }

        client
            .query("CREATE TABLE IF NOT EXISTS users (" +
                    "${DatabaseConst.ID} SERIAL NOT NULL," +
                    "${DatabaseConst.LOGIN} TEXT NOT NULL," +
                    "${DatabaseConst.IMAGE} BYTEA NOT NULL," +
                    "${DatabaseConst.PASSWORD_HASH} INT NOT NULL," +
                    "PRIMARY KEY (${DatabaseConst.ID})" +
                    ")").execute { result ->
                if (result.succeeded()) {
                    println("Users table created")
                } else {
                    println("Users table not created: $result")
                }
            }

        client
            .query("CREATE TABLE IF NOT EXISTS workspaces_users (" +
                    "${DatabaseConst.WORKSPACE_ID} INT NOT NULL," +
                    "${DatabaseConst.USER_ID} INT NOT NULL," +
                    "PRIMARY KEY (${DatabaseConst.WORKSPACE_ID}, ${DatabaseConst.USER_ID})," +
                    "FOREIGN KEY (${DatabaseConst.WORKSPACE_ID}) REFERENCES workspaces(${DatabaseConst.ID}) ON DELETE CASCADE," +
                    "FOREIGN KEY (${DatabaseConst.USER_ID}) REFERENCES users(${DatabaseConst.ID}) ON DELETE CASCADE" +
                    ")").execute { result ->
                if (result.succeeded()) {
                    println("Workspaces_users table created")
                } else {
                    println("Workspaces_users table not created: $result")
                }
            }
    }
}