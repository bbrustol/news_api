package com.bbrustol.uikit.utils

import android.content.Context
import androidx.biometric.BiometricManager.Authenticators.*
import androidx.biometric.BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE
import androidx.biometric.BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED
import androidx.biometric.BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE
import androidx.biometric.BiometricManager.BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED
import androidx.biometric.BiometricManager.BIOMETRIC_ERROR_UNSUPPORTED
import androidx.biometric.BiometricManager.BIOMETRIC_STATUS_UNKNOWN
import androidx.biometric.BiometricManager.BIOMETRIC_SUCCESS
import androidx.biometric.BiometricManager.from

object Biometric {
    fun check(context: Context) = when (from(context).canAuthenticate(BIOMETRIC_STRONG)) {
            BIOMETRIC_SUCCESS -> true
            BIOMETRIC_ERROR_NO_HARDWARE,
            BIOMETRIC_ERROR_HW_UNAVAILABLE,
            BIOMETRIC_ERROR_NONE_ENROLLED,
            BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED,
            BIOMETRIC_ERROR_UNSUPPORTED,
            BIOMETRIC_STATUS_UNKNOWN -> false

            else -> false
        }
}