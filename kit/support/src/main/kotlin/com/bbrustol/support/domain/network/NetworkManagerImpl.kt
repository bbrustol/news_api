package com.bbrustol.support.domain.network

import android.content.Context
import com.bbrustol.support.domain.usecase.error.exception.NetworkException
import com.bbrustol.support.extesion.isNetworkAvailable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkManagerImpl @Inject constructor(private val context: Context) : NetworkManager {
    /**
     * Checks if Internet is available or throws a [NetworkException]
     *
     * @throws NetworkException when internet is not available
     */
    @Throws(NetworkException::class)
    override fun checkConnectivity() {
        if (!context.isNetworkAvailable()) {
            throw NetworkException("Not Available")
        }
    }

}
