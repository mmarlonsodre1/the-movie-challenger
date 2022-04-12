@file:Suppress("MemberVisibilityCanBePrivate")

package com.example.data_remote.utils

import org.koin.core.KoinComponent
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class RequestWrapperImpl : RequestWrapper, KoinComponent {

    override suspend fun <T> wrapperGenericResponse(
        call: suspend () -> T
    ): T {
        return wrapper(call = call)
    }

    @Synchronized
    override suspend fun <D> wrapper(call: suspend () -> D): D {
        return try {
            call()
        } catch (unknownHostException: UnknownHostException) {
            throw ServerError(ErrorMessageEnum.INTERNET_ERROR.value)
        } catch (socketTimeoutException: SocketTimeoutException) {
            throw ServerError(ErrorMessageEnum.INTERNET_ERROR.value)
        } catch (httpException: HttpException) {
            when(httpException.code()) {
                401 -> throw UnauthorizedException()
                404 -> throw NotResourceException()
                else -> throw RemoteDataSourceException()
            }
        } catch (ioException: IOException) {
            throw ServerError(cause = ioException)
        } catch (stateException: IllegalStateException) {
            throw ServerError(cause = stateException)
        }
    }
}