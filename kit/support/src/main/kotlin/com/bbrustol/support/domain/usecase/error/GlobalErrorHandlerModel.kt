package com.bbrustol.support.domain.usecase.error

data class GlobalErrorHandlerModel(
    val globalErrorType: GlobalErrorType,
    val onGenericError: (suspend () -> Unit)? = null,
    val onNetworkUnavailable: (suspend () -> Unit)? = null,
    val dismissAction: (() -> Unit)? = null
)
