package com.ipetrov.community.api.handler.base

import com.google.gson.Gson
import com.ipetrov.community.helpers.Verifiable
import com.ipetrov.community.helpers.fromJsonVerify
import io.vertx.ext.web.RoutingContext

abstract class BodyHandler<T : Verifiable>(private val clazz: Class<T>, gson: Gson) : BaseHandler(gson) {

    override fun handle(event: RoutingContext) {
        try {
            handleBody(event)
        } catch (t: Throwable) {
            event.unknownErrorResponse(t, this)
        }
    }

    private fun handleBody(event: RoutingContext, onSuccessBlock: (element: T) -> Unit = {}) {
        event.request().bodyHandler { buffer ->
            try {
                val requestBody = parseJson(buffer.toString())
                onSuccess(event, requestBody)
                onSuccessBlock(requestBody)
            } catch (e: Throwable) {
                event.invalidRequestBodyResponse()
            }
        }
    }

    private fun parseJson(json: String): T {
        return gson.fromJsonVerify(json, clazz)
    }

    abstract fun onSuccess(event: RoutingContext, requestBody: T)
}