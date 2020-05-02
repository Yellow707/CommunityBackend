package com.ipetrov.community.main

import com.ipetrov.community.api.MainVerticle
import io.vertx.core.Vertx

object Application {
    @JvmStatic
    fun main(args: Array<String>) {

        Vertx.vertx().deployVerticle(MainVerticle())
    }
}