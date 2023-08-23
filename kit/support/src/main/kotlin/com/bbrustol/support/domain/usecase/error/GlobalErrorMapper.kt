package com.bbrustol.support.domain.usecase.error

import javax.inject.Inject
import javax.inject.Singleton

fun interface GlobalErrorMapper {
    fun map(throwable: Throwable): GlobalErrorType
}

@Singleton
class GlobalErrorMapperDefault @Inject constructor() : GlobalErrorMapper {
    override fun map(throwable: Throwable): GlobalErrorType {
        return GlobalErrorType.INJECTION_HILT
    }
}
