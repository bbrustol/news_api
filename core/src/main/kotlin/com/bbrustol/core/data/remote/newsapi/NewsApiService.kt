package com.bbrustol.core.data.remote.newsapi


import com.bbrustol.core.data.remote.newsapi.model.response.headline.HeadlineResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

fun interface NewsApiService {
    @GET("top-headlines")
    suspend fun getHeadline(
        @Query("sources") sources: String,
        @Query("apikey") apiKey: String
    ): Response<HeadlineResponse>
}