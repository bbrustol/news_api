package com.bbrustol.features

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bbrustol.features.home.compose.ErrorScreen

@Composable
fun ShowGenericError(message: String, onRetryAction:() -> Unit) {
    ErrorScreen(message = message, onRetryAction)
}

@Composable
fun ShowError(code: Int, message: String?, onRetryAction:() -> Unit) {
    ErrorScreen("code error: $code - $message", onRetryAction )
}

@Composable
fun IsLoading() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}