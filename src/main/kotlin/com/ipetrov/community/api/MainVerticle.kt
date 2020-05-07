package com.ipetrov.community.api

import com.google.gson.Gson
import com.ipetrov.community.api.handler.auth.RegistrationHandler
import com.ipetrov.community.dao.AccessTokenDao
import com.ipetrov.community.dao.UserDao
import com.ipetrov.community.database.Database
import com.ipetrov.community.service.UserService
import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import io.vertx.ext.web.Router

class MainVerticle : AbstractVerticle() {

    override fun start(startFuture: Future<Void>) {
        super.start()

        val database = Database(vertx)
        val gson = Gson()
        val userDao = UserDao(database)
        val accessTokenDao = AccessTokenDao(database)
        val userService = UserService(userDao, accessTokenDao)

        val router = Router.router(vertx)
        router.post("/registration").handler(RegistrationHandler(gson, userService))

        vertx.createHttpServer().requestHandler(router::handle).listen(8080)
        println("Server started")
    }
}