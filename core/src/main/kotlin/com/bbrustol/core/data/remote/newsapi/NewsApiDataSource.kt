package com.bbrustol.core.data.remote.newsapi

import com.bbrustol.core.BuildConfig
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsApiDataSource @Inject constructor(private val newsApiService: NewsApiService) {

    suspend fun getHeadline(source:String) = newsApiService.getHeadline(source, BuildConfig.API_KEY)
}