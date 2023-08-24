package com.bbrustol.core.data.remote.newsapi

import com.bbrustol.core.BuildConfig
import com.bbrustol.core.data.remote.newsapi.model.response.headline.HeadlineResponse
import com.bbrustol.support.domain.usecase.error.exception.NetworkException
import com.bbrustol.support.domain.usecase.error.exception.ServiceException
import com.bbrustol.support.extesion.map
import com.bbrustol.support.infrastructure.Network
import com.bbrustol.support.infrastructure.Network.*
import com.bbrustol.support.utils.Either
import javax.inject.Inject
import javax.inject.Singleton

fun interface NewsApiDataSource {
    @Throws(NetworkException::class, ServiceException::class)
    suspend fun getHeadline(): Either<HeadlineResponse, ServiceErrorResponse>
}

@Singleton
class NewsApiDataSourceImpl @Inject constructor(
    private val newsApiService: NewsApiService,
    private val network: Network
) : NewsApiDataSource {

    override suspend fun getHeadline(): Either<HeadlineResponse, ServiceErrorResponse> =

        if (BuildConfig.API_KEY.isNotEmpty()) {
            network.handleApi {
                newsApiService.getHeadline(
                    BuildConfig.SOURCE,
                    BuildConfig.API_KEY
                )
            }
                .map(
                    onSuccess = { it.data },
                    onFailure = { it })
        } else {
            throw ServiceException(NO_API_KEY)
        }

    companion object {
        const val NO_API_KEY = "1234"
    }
}