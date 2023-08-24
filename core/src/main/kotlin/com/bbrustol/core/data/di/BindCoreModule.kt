package com.bbrustol.core.data.di

import com.bbrustol.core.data.remote.newsapi.NewsApiDataSource
import com.bbrustol.core.data.remote.newsapi.NewsApiDataSourceImpl
import com.bbrustol.core.data.repository.HeadlineRepositoryImpl
import com.bbrustol.domain.repository.HeadlineRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BindCoreModule {

    @Binds
    abstract fun bindHeadlineRepository(
        headlineRepositoryImpl: HeadlineRepositoryImpl
    ): HeadlineRepository

    @Binds
    abstract fun bindHeadlineDataSource(
        hewsApiDataSourceImpl: NewsApiDataSourceImpl
    ): NewsApiDataSource
}
