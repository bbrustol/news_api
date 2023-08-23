package com.bbrustol.support.utils

import com.bbrustol.support.utils.Either.*

sealed class Either<out T, out E> {
    data class Success<out T>(val data: T) : Either<T, Nothing>()
    data class Failure<out E>(val error: E) : Either<Nothing, E>()
}

fun eitherEmpty() = Success(Unit)
fun <T> eitherSuccess(data: T) = Success(data)
fun <T> eitherFailure(error: T) = Failure(error)