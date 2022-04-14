package com.example.data_remote.util

import com.example.data_remote.utils.RequestWrapper

class RequestWrapperTest : RequestWrapper {
    override suspend fun <T> wrapperGenericResponse(
        call: suspend () -> T
    ): T {
        return  wrapper(call = call) ?: throw NullPointerException()
    }

    override suspend fun <D> wrapper(call: suspend () -> D): D {
        return  call()
    }
}
