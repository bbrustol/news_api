package com.bbrustol.core.data.repository

import com.bbrustol.core.BuildConfig
import com.bbrustol.core.data.di.IoDispatcher
import com.bbrustol.core.data.infrastructure.ApiError
import com.bbrustol.core.data.infrastructure.ApiResult
import com.bbrustol.core.data.infrastructure.handleApi
import com.bbrustol.core.data.remote.newsapi.NewsApiDataSource
import com.bbrustol.core.data.remote.newsapi.model.response.headline.HeadlineResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsApiRepository @Inject constructor(
    private val remoteNewsApiDataSource: NewsApiDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    fun getHeadline(source: String = BuildConfig.SOURCE): Flow<ApiResult<HeadlineResponse>> {
        return flow {
            emit(if (BuildConfig.API_KEY.isNotEmpty()) {
                handleApi { remoteNewsApiDataSource.getHeadline(source) }
            } else {
                ApiError(code = NO_API_KEY, message = "")
            })
        }.flowOn(ioDispatcher)
    }

    companion object {
        const val NO_API_KEY = 1234
    }
}