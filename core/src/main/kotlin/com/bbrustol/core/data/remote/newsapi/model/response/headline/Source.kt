package com.bbrustol.core.data.remote.newsapi.model.response.headline

import com.squareup.moshi.Json

data class Source(
    @Json(name = "id")
    val id: String?,
    @Json(name = "name")
    val name: String
)