package com.bbrustol.support.di

import com.bbrustol.support.domain.usecase.SafeFlowUseCaseDefault
import com.bbrustol.support.domain.usecase.SafeFlowUseCaseDelegate
import com.bbrustol.support.domain.usecase.error.GlobalErrorMapper
import com.bbrustol.support.domain.usecase.error.GlobalErrorMapperDefault
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SafeFlowUseCaseDelegateModule {
    @Binds
    abstract fun bindSafeFlowUseCaseDelegate(
        safeFlowUseCaseDefault: SafeFlowUseCaseDefault
    ): SafeFlowUseCaseDelegate

    @Binds
    abstract fun bindGlobalErrorMapper(
        globalErrorMapperDefault: GlobalErrorMapperDefault
    ): GlobalErrorMapper
}
