package com.example.data_remote.utils

import com.example.domain.exception.DataSourceException
import com.example.domain.exception.NotResourceException
import com.example.domain.exception.UnauthorizedException

enum class ErrorMessageEnum(val value: String) {
    INTERNET_ERROR("Ocorreu um problema ao validar as informações. Verifique sua conexão de internet e tente novamente"),
    GENERIC_ERROR("Ocorreu um erro. Por favor tente novamente mais tarde!"),
    AUTHENTICATION_TIMEOUT_ERROR("Chave Api inválida"),
    NOT_RESOURCE_ERROR("O recurso solicitado não foi encontrado"),
}

open class RemoteDataSourceException(
    message: String = ErrorMessageEnum.GENERIC_ERROR.value,
    cause: Throwable? = null
) : DataSourceException(message = message, cause = cause)

class ServerError(
    message: String = ErrorMessageEnum.GENERIC_ERROR.value,
    cause: Throwable? = null
) : DataSourceException(message = message, cause = cause)

class UnauthorizedException(
    message: String = ErrorMessageEnum.AUTHENTICATION_TIMEOUT_ERROR.value
) : UnauthorizedException(message)

class NotResourceException(
    message: String = ErrorMessageEnum.NOT_RESOURCE_ERROR.value
) : NotResourceException(message)