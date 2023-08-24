package com.bbrustol.core.data.remote.newsapi.model.response.headline

sealed class HeadlineErrorResponse {
    data class Failure(val code: String?, val description: String?) : HeadlineErrorResponse()
}