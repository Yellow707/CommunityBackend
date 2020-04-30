package com.ipetrov.community.main

import com.google.gson.Gson
import com.ipetrov.community.api.handler.auth.RegistrationHandler
import org.koin.dsl.module

val dataModule = module {
    factory { Gson() }
}

val handlerModule = module {
    factory { RegistrationHandler(get()) }
}