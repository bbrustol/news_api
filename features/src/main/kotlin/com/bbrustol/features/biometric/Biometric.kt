package com.bbrustol.features.biometric

import android.content.Context
import androidx.biometric.BiometricManager.Authenticators
import androidx.biometric.BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE
import androidx.biometric.BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED
import androidx.biometric.BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE
import androidx.biometric.BiometricManager.BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED
import androidx.biometric.BiometricManager.BIOMETRIC_ERROR_UNSUPPORTED
import androidx.biometric.BiometricManager.BIOMETRIC_STATUS_UNKNOWN
import androidx.biometric.BiometricManager.BIOMETRIC_SUCCESS
import androidx.biometric.BiometricManager.from
import androidx.biometric.BiometricPrompt
import androidx.biometric.BiometricPrompt.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity

object Biometric {

    fun status(con: Context): Boolean {
        var result = false
        val biometricManager = from(con)

        when (biometricManager.canAuthenticate(Authenticators.DEVICE_CREDENTIAL or Authenticators.BIOMETRIC_STRONG)) {
            BIOMETRIC_SUCCESS -> result = true
            BIOMETRIC_ERROR_NO_HARDWARE -> result = false
            BIOMETRIC_ERROR_HW_UNAVAILABLE -> result = false
            BIOMETRIC_ERROR_NONE_ENROLLED -> result = false
            BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED ->
                result = true

            BIOMETRIC_ERROR_UNSUPPORTED ->
                result = true

            BIOMETRIC_STATUS_UNKNOWN ->
                result = false
        }

        return result
    }

    fun authenticate(
        activity: FragmentActivity,
        title: String,
        subtitle: String,
        description: String,
        negativeText: String,
        onError: (Int, CharSequence) -> Unit,
        onSuccess: (AuthenticationResult) -> Unit,
        onFailed: () -> Unit,
    ) {
        val executor = ContextCompat.getMainExecutor(activity)
        val biometricPrompt = BiometricPrompt(activity, executor,
            object : AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    onError(errorCode, errString)
                }

                override fun onAuthenticationSucceeded(result: AuthenticationResult) {
                    onSuccess(result)
                }

                override fun onAuthenticationFailed() {
                    onFailed()
                }
            })

        val promptInfo: PromptInfo = PromptInfo.Builder()
            .setTitle(title)
            .setSubtitle(subtitle)
            .setDescription(description)
            .setNegativeButtonText(negativeText)
            .build()
        biometricPrompt.authenticate(promptInfo)

    }
}