package com.bbrustol.support.domain.usecase

import androidx.annotation.CheckResult
import com.bbrustol.support.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

abstract class FlowUseCase<T, R>(@IoDispatcher private val ioDispatcher: CoroutineDispatcher) {

    @CheckResult
    fun prepare(input: T) = prepareFlow(input).flowOn(ioDispatcher)

    /**
     * prepareFlow Return a [Flow] that will be executed in the specified [CoroutineContext] ([dispatcher.io()] by default).
     *
     * There's no need to call to [flowOn] in subclasses.
     */
    protected abstract fun prepareFlow(input: T): Flow<R>
}
