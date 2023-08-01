package com.bbrustol.core.data.remote.newsapi.model.response.headline

import com.squareup.moshi.Json

data class HeadlineResponse(
    @Json(name = "articles")
    val articles: List<Article>,
    @Json(name = "status")
    val status: String,
    @Json(name = "totalResults")
    val totalResults: Int
)