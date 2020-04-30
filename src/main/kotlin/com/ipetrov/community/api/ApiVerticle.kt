package com.ipetrov.community.api

import com.ipetrov.community.api.handler.auth.RegistrationHandler
import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import io.vertx.ext.web.Router
import org.koin.core.KoinComponent

class ApiVerticle : AbstractVerticle(), KoinComponent {

    override fun start(startFuture: Future<Void>) {
        super.start()

        val router = Router.router(vertx)
        router.post("/registration").handler(getKoin().get<RegistrationHandler>())

        vertx.createHttpServer().requestHandler(router::handle).listen(8080)
        println("Server started")
    }
}