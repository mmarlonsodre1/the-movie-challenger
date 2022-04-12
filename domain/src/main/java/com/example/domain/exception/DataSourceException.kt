package com.example.domain.exception

open class DataSourceException(
    message: String,
    cause: Throwable? = null
) : Exception(message, cause)
