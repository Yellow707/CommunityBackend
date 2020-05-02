package com.ipetrov.community.api.handler.auth

import com.google.gson.Gson
import com.ipetrov.community.api.handler.base.BodyHandler
import com.ipetrov.community.api.model.AuthModel
import com.ipetrov.community.api.response.BaseResponse
import com.ipetrov.community.exceptions.ServerException
import io.vertx.ext.web.RoutingContext

abstract class AbstractAuthHandler(gson: Gson) : BodyHandler<AuthModel>(AuthModel::class.java, gson) {

    override fun onSuccess(event: RoutingContext, requestBody: AuthModel) {
        try {
            if (validateAuthRequest(requestBody, event)) return
            onAuthRequestValid(requestBody, event)
        } catch (e: Throwable) {
            event.unknownErrorResponse(e, this)
        }
    }

    private fun validateAuthRequest(requestBody: AuthModel, event: RoutingContext): Boolean {
        if (requestBody.login.length < 6) {
            event.endResponse(BaseResponse(BaseResponse.Status.ERROR, ServerException.LoginLengthException()), 400)
            return true
        }

        if (requestBody.passwordHash.length < 6) {
            event.endResponse(BaseResponse(BaseResponse.Status.ERROR, ServerException.PasswordLengthException()), 400)
            return true
        }
        return false
    }

    protected abstract fun onAuthRequestValid(requestBody: AuthModel, event: RoutingContext)
}