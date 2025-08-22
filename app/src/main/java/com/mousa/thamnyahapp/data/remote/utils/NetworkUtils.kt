package com.mousa.thamnyahapp.data.remote.utils

import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.ConnectException

suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): T {
    return try {
        val apiResponse = apiCall.invoke()
        if (!apiResponse.isSuccessful) throw NetworkError.General()
        else if (apiResponse.body() == null) throw NetworkError.ResponseIsNull()
        else apiResponse.body()!!
    } catch (throwable: Throwable) {
        throw throwable.toFailure()
    }
}

fun Throwable.toFailure(): NetworkError {
    return when (this) {
        is HttpException -> {
            when (code()) {
                in 300 until 400 -> NetworkError.Unauthorized()
                in 400 until 500 -> NetworkError.Validation()
                in 500 until 600 -> NetworkError.ServerError()
                else -> NetworkError.General()
            }
        }
        is ConnectException -> NetworkError.NetworkConnection()
        is IOException -> NetworkError.NetworkConnection()
        else -> NetworkError.General()
    }
}

sealed class NetworkError: Throwable() {
    data class General(override val message: String = "Something went wrong. Please try again later.") : NetworkError()
    data class Unauthorized(override val message: String = "Not Authorized.") : NetworkError()
    data class Validation(override val message: String = "Data not valid.") : NetworkError()
    data class ServerError(override val message: String = "Server is busy. Please try again later.") : NetworkError()
    data class NetworkConnection(override val message: String = "Check your internet connection.") : NetworkError()
    data class ResponseIsNull(override val message: String?=null) : NetworkError()
}