package com.ipetrov.community.api.handler.auth

import com.google.gson.Gson
import com.ipetrov.community.api.model.AuthModel
import com.ipetrov.community.api.response.AuthorizationResponse
import com.ipetrov.community.api.response.BaseResponse
import com.ipetrov.community.service.IUserService
import com.ipetrov.community.service.UserService
import io.vertx.ext.web.RoutingContext

class AuthorizationHandler(gson: Gson, userService: IUserService) : AbstractAuthHandler(gson) {

    var userService = userService

    override fun onAuthRequestValid(requestBody: AuthModel, event: RoutingContext) {
        userService.authUser(requestBody) { accessToken ->
            if (accessToken != null) {
                event.endResponse(BaseResponse(AuthorizationResponse(accessToken)))
            } else {
                event.endResponse(BaseResponse(BaseResponse.Status.ERROR, "Wrong login or password"))
            }

        }
    }
}