package com.ipetrov.community.api.response

import com.google.gson.annotations.SerializedName
import com.ipetrov.community.exceptions.ServerException



class BaseResponse(
    val status: String,
    val data: Any?,
    @SerializedName("error_message")
    val errorMessage: String?
) {
    enum class Status(val value: String) {
        SUCCESS("success"),
        ERROR("error"),
        UNKNOWN_ERROR("unknown_error")
    }

    constructor(data: Any) : this(Status.SUCCESS.value, data, null) {}
    constructor(status: Status, errorMessage: String) : this(status.value, null, errorMessage)
    constructor(status: Status, throwableException: ServerException) : this(status.value, null, throwableException.message)
    constructor(status: Status) : this(status.value, null, null)

    companion object{
        fun justSuccess() = BaseResponse(Status.SUCCESS)
    }
}