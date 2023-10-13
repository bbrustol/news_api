package com.bbrustol.features.home.compose

import android.hardware.biometrics.BiometricPrompt.BIOMETRIC_ERROR_NO_BIOMETRICS
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.bbrustol.uikit.compose.biometric.BiometricAuth
import com.bbrustol.uikit.compose.biometric.BiometricModel
import com.bbrustol.uikit.utils.Screen
import com.bbrustol.uikit.R as UIKIT_R

@Composable
fun BiometricScreen(navController: NavHostController) {
    val biometricModel = BiometricModel(
        title = stringResource(UIKIT_R.string.biometric_authentication),
        subtitle = stringResource(UIKIT_R.string.biometric_auth_proceed),
        description = stringResource(UIKIT_R.string.biometric_auth_must),
        negativeText = stringResource(UIKIT_R.string.biometric_auth_negative_button)
    )

    val context = LocalContext.current

    BiometricAuth(
        biometricModel,
        onSuccess = { navController.navigate(Screen.GetHeadline.route) },
        onError = { errorCode, errorString ->
            if (errorCode == BIOMETRIC_ERROR_NO_BIOMETRICS) {
                navController.navigate(Screen.GetHeadline.route)
            } else {
                Toast.makeText(
                    context,
                    context.getString(UIKIT_R.string.biometric_auth_error, errorCode, errorString),
                    Toast.LENGTH_SHORT
                ).show()
            }
        },
        onFailed = {
            Toast.makeText(
                context,
                context.getString(UIKIT_R.string.biometric_auth_failed),
                Toast.LENGTH_SHORT
            ).show()
        }
    )
}