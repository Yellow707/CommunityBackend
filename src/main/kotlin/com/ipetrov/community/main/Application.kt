package com.ipetrov.community.main

import com.ipetrov.community.api.ApiVerticle
import io.vertx.core.Vertx
import org.koin.core.context.startKoin

object Application {
    @JvmStatic
    fun main(args: Array<String>) {
        startKoin {
            modules(listOf(dataModule, handlerModule))
        }

        Vertx.vertx().deployVerticle(ApiVerticle())
    }
}