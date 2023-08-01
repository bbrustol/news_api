package com.bbrustol.core.data.di

import android.content.Context
import com.bbrustol.core.BuildConfig
import com.bbrustol.core.data.infrastructure.ApiCacheInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.net.ssl.*

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    companion object {
        private const val TIMEOUT:Long = 15
    }

    @Provides
    @Named("BASE_URL")
    fun provideBaseUrl() = BuildConfig.BASE_URL

    @Provides
    fun providesCacheInterceptor(@ApplicationContext context: Context): Interceptor = ApiCacheInterceptor(context)

    @Provides
    @Named("BUILDER")
    fun providesBuilder(@ApplicationContext context: Context) = OkHttpClient
        .Builder()
        .cache(Cache(context.cacheDir, (5 * 1024 * 1024).toLong()))
        .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT, TimeUnit.SECONDS)
        .connectTimeout(TIMEOUT, TimeUnit.SECONDS)

    @Provides
    @Named("OK_HTTP_CLIENT")
    fun provideOkHttpClient(
        @Named("BUILDER") okHttpClientBuilder: OkHttpClient.Builder,
        interceptor: Interceptor) =
        if (BuildConfig.DEBUG)
            okHttpClientBuilder
            .addInterceptor(interceptor)
            .build()
        else okHttpClientBuilder
            .build()

    @Provides
    fun provideRetrofit(
        @Named("OK_HTTP_CLIENT") okHttpClient: OkHttpClient,
        @Named("BASE_URL") baseUrl: String
    ): Retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build()
}
