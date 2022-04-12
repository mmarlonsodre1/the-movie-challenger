package com.example.data_remote.utils


interface RequestWrapper {

    suspend fun <T> wrapperGenericResponse(
        call: suspend () -> T
    ): T

    suspend fun <D> wrapper(
        call: suspend () -> D
    ): D
}