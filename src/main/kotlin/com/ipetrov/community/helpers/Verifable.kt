package com.ipetrov.community.helpers

interface Verifiable {

    fun verify()
    fun error(a: Any):Nothing = throw VerifiableException(a)

    private class VerifiableException(a: Any?) : RuntimeException("Can't verify ${a?.javaClass?.name}")
}