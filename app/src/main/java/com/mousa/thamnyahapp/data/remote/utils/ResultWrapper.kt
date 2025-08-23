package com.mousa.thamnyahapp.data.remote.utils

sealed class ResultWrapper<out T> {
    data class Success<out T>(val data: T) : ResultWrapper<T>()
    data class Error(val error: NetworkError) : ResultWrapper<Nothing>()
}