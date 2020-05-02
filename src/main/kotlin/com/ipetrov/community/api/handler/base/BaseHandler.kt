package com.ipetrov.community.api.handler.base

import com.google.gson.Gson
import com.ipetrov.community.api.response.BaseResponse
import com.ipetrov.community.exceptions.ServerException
import io.netty.handler.codec.http.HttpHeaderNames
import io.netty.handler.codec.http.HttpHeaderValues
import io.vertx.core.Handler
import io.vertx.ext.web.RoutingContext

abstract class BaseHandler(protected val gson: Gson) : Handler<RoutingContext>{

    protected fun RoutingContext.endResponse(responseBody: BaseResponse, code: Int = 200) {
        val response = request().response()
        if (response.ended()) return
        val respObj = gson.toJson(responseBody)
        response.putHeader(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON)
        response.end(respObj)
    }

    protected fun RoutingContext.invalidRequestBodyResponse() {
        endResponse(BaseResponse(BaseResponse.Status.ERROR, ServerException.InvalidRequestBodyException()), 400)
    }

    protected fun RoutingContext.unknownErrorResponse(e: Throwable, target: Any) {
        endResponse(
            BaseResponse(BaseResponse.Status.UNKNOWN_ERROR, "${target.javaClass.simpleName} - ${e.localizedMessage}"),
            500)
    }
}