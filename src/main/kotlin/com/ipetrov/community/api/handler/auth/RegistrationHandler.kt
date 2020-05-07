package com.ipetrov.community.api.handler.auth

import com.google.gson.Gson
import com.ipetrov.community.api.model.AuthModel
import com.ipetrov.community.api.response.AuthorizationResponse
import com.ipetrov.community.api.response.BaseResponse
import com.ipetrov.community.service.IUserService
import io.vertx.ext.web.RoutingContext

class RegistrationHandler(gson: Gson, userService: IUserService): AbstractAuthHandler(gson) {

    val userService = userService

    override fun onAuthRequestValid(requestBody: AuthModel, event: RoutingContext) {
        //TODO: Add user to database
        println("Registration handler")

        userService.registerUser(requestBody) { accessToken ->
            if (accessToken != null) {
                event.endResponse(BaseResponse(AuthorizationResponse(accessToken)))
            } else {
                event.endResponse(BaseResponse(BaseResponse.Status.ERROR, "Access token null"))
            }
        }
    }
}