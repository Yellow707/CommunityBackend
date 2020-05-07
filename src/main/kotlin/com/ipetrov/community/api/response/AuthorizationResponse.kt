package com.ipetrov.community.api.response

import com.google.gson.annotations.SerializedName

class AuthorizationResponse(
        @SerializedName("access_token")
        val accessToken: String
)