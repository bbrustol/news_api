package com.bbrustol.domain.usecase

import com.bbrustol.domain.model.HeadlineErrorModel
import com.bbrustol.domain.model.HeadlineModel
import com.bbrustol.domain.repository.HeadlineRepository
import com.bbrustol.support.di.IoDispatcher
import com.bbrustol.support.domain.usecase.FlowUseCase
import com.bbrustol.support.utils.Either
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HeadlineUseCase
@Inject
constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val homeRepository: HeadlineRepository
) : FlowUseCase<Unit, Either<List<HeadlineModel>, HeadlineErrorModel>>(ioDispatcher) {
    override fun prepareFlow(input: Unit): Flow<Either<List<HeadlineModel>, HeadlineErrorModel>> =
        flow {
            emit(homeRepository.getHeadline())
        }
}
