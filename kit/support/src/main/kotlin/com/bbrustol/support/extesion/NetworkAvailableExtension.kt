package com.bbrustol.support.extesion

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities.*
import com.bbrustol.support.domain.usecase.error.exception.NetworkException

@SuppressLint("MissingPermission")
@Throws(NetworkException::class)
fun Context.isNetworkAvailable(): Boolean {
    val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork ?: return false
    val networkCapabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

    return with(networkCapabilities) {
        when {
            hasTransport(TRANSPORT_WIFI) -> true
            hasTransport(TRANSPORT_CELLULAR) -> true
            hasTransport(TRANSPORT_ETHERNET) -> true
            hasTransport(TRANSPORT_BLUETOOTH) -> true
            else -> false
        }
    }
}
