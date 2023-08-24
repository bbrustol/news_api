package com.bbrustol.domain.repository

import com.bbrustol.domain.model.HeadlineErrorModel
import com.bbrustol.domain.model.HeadlineModel
import com.bbrustol.support.utils.Either

interface HeadlineRepository {
    suspend fun getHeadline(): Either<List<HeadlineModel>, HeadlineErrorModel>
}

//typealias HeadlineRepository = Either<HeadlineModel, HeadlineErrorModel>
