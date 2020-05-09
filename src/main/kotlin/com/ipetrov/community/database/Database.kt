package com.ipetrov.community.database

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

    private fun createTablesIfNotExists() {
        val workspacesQuery = "CREATE TABLE IF NOT EXISTS workspaces (" +
                    "${DatabaseConst.ID} SERIAL NOT NULL," +
                    "${DatabaseConst.TITLE} TEXT NOT NULL," +
                    "${DatabaseConst.DESCRIPTION} TEXT NOT NULL," +
                    "${DatabaseConst.CREATORID} INT NOT NULL," +
                    "PRIMARY KEY (${DatabaseConst.ID})" +
                    ")"

        val usersQuery = "CREATE TABLE IF NOT EXISTS users (" +
                "${DatabaseConst.ID} SERIAL NOT NULL," +
                "${DatabaseConst.LOGIN} TEXT UNIQUE NOT NULL," +
                "${DatabaseConst.IMAGE} BYTEA," +
                "${DatabaseConst.PASSWORD} TEXT NOT NULL," +
                "PRIMARY KEY (${DatabaseConst.ID})" +
                ")"

        val workspacesUsersQuery = "CREATE TABLE IF NOT EXISTS workspaces_users (" +
                "${DatabaseConst.WORKSPACE_ID} SERIAL NOT NULL," +
                "${DatabaseConst.USER_ID} INT NOT NULL," +
                "PRIMARY KEY (${DatabaseConst.WORKSPACE_ID}, ${DatabaseConst.USER_ID})," +
                "FOREIGN KEY (${DatabaseConst.WORKSPACE_ID}) REFERENCES workspaces(${DatabaseConst.ID}) ON DELETE CASCADE," +
                "FOREIGN KEY (${DatabaseConst.USER_ID}) REFERENCES users(${DatabaseConst.ID}) ON DELETE CASCADE" +
                ")"

        val accessTokenQuery = "CREATE TABLE IF NOT EXISTS access_tokens (" +
                "${DatabaseConst.ID} SERIAL NOT NULL, " +
                "${DatabaseConst.USER_ID} INT NOT NULL, " +
                "${DatabaseConst.TOKEN} TEXT NOT NULL, " +
                "${DatabaseConst.EXPIRATION_TIME} INTEGER NOT NULL, " +
                "PRIMARY KEY (${DatabaseConst.ID}), " +
                "FOREIGN KEY (${DatabaseConst.USER_ID}) REFERENCES users(${DatabaseConst.ID}) ON DELETE CASCADE" +
                ")"

        val refreshTokenQuery = "CREATE TABLE IF NOT EXISTS refresh_tokens (" +
                "${DatabaseConst.ID} SERIAL NOT NULL, " +
                "${DatabaseConst.USER_ID} INT NOT NULL, " +
                "${DatabaseConst.TOKEN} TEXT NOT NULL, " +
                "${DatabaseConst.EXPIRATION_TIME} INTEGER NOT NULL, " +
                "PRIMARY KEY (${DatabaseConst.ID}), " +
                "FOREIGN KEY (${DatabaseConst.USER_ID}) REFERENCES users(${DatabaseConst.ID}) ON DELETE CASCADE" +
                ")"

        client.query(workspacesQuery).execute { workspacesResult ->
            if (workspacesResult.succeeded()){
                client.query(usersQuery).execute { usersResult ->
                    if (usersResult.succeeded()) {
                        client.query(workspacesUsersQuery).execute { workspacesUsersResult ->
                            if (workspacesUsersResult.succeeded()) {
                                client.query(accessTokenQuery).execute { accessTokensResult ->
                                    if (accessTokensResult.succeeded()) {
                                        client.query(refreshTokenQuery).execute { refreshTokensResult ->
                                            if (refreshTokensResult.succeeded()) {
                                                println("Database tables created")
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}