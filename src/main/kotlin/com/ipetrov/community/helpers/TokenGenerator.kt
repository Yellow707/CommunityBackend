package com.ipetrov.community.helpers

import java.util.*

object TokenGenerator {
    fun generate(): String = UUID.randomUUID().toString() + "-" + UUID.randomUUID().toString()
}