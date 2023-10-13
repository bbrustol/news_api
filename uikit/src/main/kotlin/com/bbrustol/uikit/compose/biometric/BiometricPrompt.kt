package com.bbrustol.uikit.compose.biometric

import androidx.biometric.BiometricManager.Authenticators.*
import androidx.biometric.BiometricPrompt
import androidx.biometric.BiometricPrompt.AuthenticationCallback
import androidx.biometric.BiometricPrompt.AuthenticationResult
import androidx.biometric.BiometricPrompt.PromptInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity

@Composable
fun BiometricAuth(
    biometricModel: BiometricModel,
    onError: (Int, CharSequence) -> Unit,
    onSuccess: (AuthenticationResult) -> Unit,
    onFailed: () -> Unit,
) {
    val context = LocalContext.current

    LaunchedEffect(key1 = context) {
        val fragmentActivity = context as? FragmentActivity ?: return@LaunchedEffect
        val executor = ContextCompat.getMainExecutor(fragmentActivity)
        val biometricPrompt = BiometricPrompt(fragmentActivity, executor,
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

        with(biometricModel) {
            val promptInfo = PromptInfo.Builder()
                .setTitle(title)
                .setSubtitle(subtitle)
                .setDescription(description)
                .setNegativeButtonText(negativeText)
                .setAllowedAuthenticators(BIOMETRIC_STRONG or BIOMETRIC_WEAK)
                .build()
            biometricPrompt.authenticate(promptInfo)
        }
    }
}
