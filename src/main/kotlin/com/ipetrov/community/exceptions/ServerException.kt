package com.ipetrov.community.exceptions

sealed class ServerException(message: String) : Throwable(message) {

    class InvalidRequestBodyException() : ServerException(INVALID_REQUEST_BODY)
    class LoginLengthException() : ServerException(INVALID_LOGIN_LENGTH)
    class PasswordLengthException() : ServerException(INVALID_PASSWORD_LENGTH)

    companion object {
        const val INVALID_REQUEST_BODY = "Invalid request body."
        const val INVALID_LOGIN_LENGTH = "Login must be longer than five characters."
        const val INVALID_PASSWORD_LENGTH = "Password must be longer than five characters."
    }
}