package com.ipetrov.community.api.handler.auth

import com.google.gson.Gson
import com.ipetrov.community.api.model.AuthModel
import com.ipetrov.community.api.response.BaseResponse
import com.ipetrov.community.service.IUserService
import io.vertx.ext.web.RoutingContext

class RegistrationHandler(gson: Gson, userService: IUserService): AbstractAuthHandler(gson) {

    val userService = userService

    override fun onAuthRequestValid(requestBody: AuthModel, event: RoutingContext) {
        //TODO: Add user to database
        print("Registration handler")

        userService.registerUser(requestBody)
        event.endResponse(BaseResponse(AuthModel("Yellow707", "successPassword")))
    }

}