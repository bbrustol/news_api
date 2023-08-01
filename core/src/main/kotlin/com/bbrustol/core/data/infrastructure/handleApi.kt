package com.bbrustol.core.data.infrastructure

import retrofit2.Response
import retrofit2.HttpException

suspend fun <T : Any> handleApi(execute: suspend () -> Response<T>): ApiResult<T> {
    return try {
            val response = execute()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                ApiSuccess(body)
            } else {
                ApiError(code = response.code(), message = response.message())
            }
        } catch (e: HttpException) {
            ApiError(code = e.code(), message = e.message())
        } catch (e: Throwable) {
            ApiException(e)
        }
}