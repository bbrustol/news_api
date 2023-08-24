package com.bbrustol.core.data.repository

import com.bbrustol.core.data.remote.newsapi.NewsApiDataSource
import com.bbrustol.domain.model.HeadlineErrorModel
import com.bbrustol.domain.model.HeadlineModel
import com.bbrustol.core.data.repository.mappers.toHeadlineErrorModel
import com.bbrustol.core.data.repository.mappers.toListHeadlineArticleModel
import com.bbrustol.domain.repository.HeadlineRepository
import com.bbrustol.support.extesion.map
import com.bbrustol.support.utils.Either
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HeadlineRepositoryImpl @Inject constructor(
    private val remoteNewsApiDataSource: NewsApiDataSource
) : HeadlineRepository {

    override suspend fun getHeadline(): Either<List<HeadlineModel>, HeadlineErrorModel> =
        remoteNewsApiDataSource.getHeadline()
            .map(onSuccess = { it.toListHeadlineArticleModel() },
                onFailure = { it.toHeadlineErrorModel() }
            )
}