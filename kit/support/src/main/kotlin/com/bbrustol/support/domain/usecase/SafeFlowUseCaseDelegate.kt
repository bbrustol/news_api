package com.bbrustol.support.domain.usecase

import com.bbrustol.support.domain.usecase.error.GlobalErrorManager
import com.bbrustol.support.extesion.logTag
import com.bbrustol.support.utils.RetailLogger
import kotlinx.coroutines.flow.catch
import javax.inject.Inject
import javax.inject.Singleton

/** Delegate to call useCases in a safe way, because handle exceptions by default */

interface SafeFlowUseCaseDelegate {
    val globalErrorManager: GlobalErrorManager

    /** Method that handle global errors by default. Use lambdas for special cases */
    fun <T, R> FlowUseCase<T, R>.safePrepare(
        input: T,
        onGenericError: (suspend () -> Unit)? = null,
        onNetworkUnavailable: (suspend () -> Unit)? = null,
        dismissAction: (() -> Unit)? = null
    ) =
        prepare(input).catch {
            RetailLogger.e(logTag, it)
            globalErrorManager.emitError(
                it,
                onGenericError,
                onNetworkUnavailable,
                dismissAction
            )
        }

    /** Method that handle global errors by default without parameter for use case. Use lambdas for special cases */
    fun <R> FlowUseCase<Unit, R>.safePrepare(
        onGenericError: (suspend () -> Unit)? = null,
        onNetworkUnavailable: (suspend () -> Unit)? = null,
        dismissAction: (() -> Unit)? = null
    ) =
        prepare(Unit).catch {
            RetailLogger.e(logTag, it)
            globalErrorManager.emitError(
                it,
                onGenericError,
                onNetworkUnavailable,
                dismissAction
            )
        }
}

@Singleton
class SafeFlowUseCaseDefault @Inject constructor(override val globalErrorManager: GlobalErrorManager) : SafeFlowUseCaseDelegate