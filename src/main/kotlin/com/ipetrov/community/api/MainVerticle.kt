package com.ipetrov.community.api

import com.google.gson.Gson
import com.ipetrov.community.model.UserModel
import io.vertx.core.AbstractVerticle
import io.vertx.core.Future

class MainVerticle : AbstractVerticle() {

    override fun start(startFuture: Future<Void>) {
        super.start()

        val server = vertx.createHttpServer()
        server.webSocketHandler { handler ->
            handler.writeTextMessage("Connected")
            handler.handler{ msg ->
                val user = Gson().fromJson(msg.toString(),UserModel::class.java)
                val userString = user.toString()
                print(user.name)
            }

        }.listen(8080) { http ->
            if (http.succeeded()) {
                startFuture.complete()
                println("HTTP server started on port 8080")
            } else {
                startFuture.fail(http.cause());
            }
        }
    }
}