package com.ipetrov.community.helpers

import com.google.gson.Gson

fun <T : Verifiable> Gson.fromJsonVerify(json: String, classOfT: Class<T>): T {
    val classObject = fromJson(json, classOfT)
    classObject.verify()
    return classObject
}