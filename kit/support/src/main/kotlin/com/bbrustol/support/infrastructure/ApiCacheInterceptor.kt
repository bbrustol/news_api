package com.bbrustol.support.infrastructure

import android.content.Context
import com.bbrustol.support.extesion.isNetworkAvailable
import okhttp3.Interceptor
import okhttp3.Response

class ApiCacheInterceptor(private val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        chain.request().newBuilder().apply {
            addHeader("Content-Type", "application/json")

            if (context.isNetworkAvailable())
                addHeader("Cache-Control", "public, max-age=" + 60)
            else
                addHeader(
                    "Cache-Control",
                    "public, only-if-cached, max-stale=" + (60 * 60 * 24 * 7)
                )
            return chain.proceed(build())
        }
    }
}