package com.bbrustol.core.data.repository.mappers

import com.bbrustol.core.data.remote.newsapi.model.response.headline.Article
import com.bbrustol.core.data.remote.newsapi.model.response.headline.HeadlineResponse
import com.bbrustol.domain.model.HeadlineErrorModel
import com.bbrustol.domain.model.HeadlineModel
import com.bbrustol.support.infrastructure.Network

fun HeadlineResponse.toListHeadlineArticleModel() = articles.toHeadlineModel()

fun List<Article>.toHeadlineModel() = map { it.toHeadlineModel() }

fun Article.toHeadlineModel() = HeadlineModel(
    id = source.id ?: "",
    author = author ?: "",
    content = content,
    description = description,
    publishedAt = publishedAt,
    sourceName = source.name,
    title = title,
    url = url,
    urlToImage = urlToImage ?: ""
)

fun Network.ServiceErrorResponse.toHeadlineErrorModel() = HeadlineErrorModel(
    code = statusCode,
    description = description ?: ""
)