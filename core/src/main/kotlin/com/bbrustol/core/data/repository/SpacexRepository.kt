package com.bbrustol.core.data.repository

import com.bbrustol.core.data.infrastructure.ApiResult
import com.bbrustol.core.data.infrastructure.handleApi
import com.bbrustol.core.data.remote.newsapi.NewsApiDataSource
import com.bbrustol.core.data.remote.newsapi.model.response.headline.HeadlineResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsApiRepository @Inject constructor(private val remoteNewsApiDataSource: NewsApiDataSource)  {
    private fun dispatcher() = Dispatchers.IO

    fun getHeadline(source: String = "bbc-news"): Flow<ApiResult<HeadlineResponse>> {
        return flow {
            emit(handleApi { remoteNewsApiDataSource.getHeadline(source) })
        }.flowOn(dispatcher())
    }
}