package com.ipetrov.community.api

import com.google.gson.Gson
import com.ipetrov.community.model.UserModel
import io.vertx.core.AbstractVerticle
import io.vertx.core.Future

class MainVerticle : AbstractVerticle() {

    override fun start(startFuture: Future<Void>) {
        super.start()
        val server = vertx.createHttpServer()
    }
}