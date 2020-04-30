package com.ipetrov.community.api.handler.auth

import com.google.gson.Gson
import com.ipetrov.community.api.model.RegistrationModel
import com.ipetrov.community.api.response.BaseResponse
import io.vertx.ext.web.RoutingContext

class RegistrationHandler(gson: Gson): AbstractAuthHandler(gson) {

    override fun onAuthRequestValid(requestBody: RegistrationModel, event: RoutingContext) {
        //TODO: Add user to database
        print("Registration handler")
        event.endResponse(BaseResponse(RegistrationModel("Yellow707", "successPassword")))
    }

}