package com.bbrustol.support.domain.network

import com.bbrustol.support.domain.usecase.error.exception.NetworkException

interface NetworkManager {
    /**
     * Checks if Internet is available or throws a [NetworkException]
     *
     * @throws NetworkException when internet is not available
     */
    @Throws(NetworkException::class) fun checkConnectivity()

}
