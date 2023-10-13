package com.bbrustol.features.home.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.bbrustol.core.data.repository.NewsApiRepository.Companion.NO_API_KEY
import com.bbrustol.features.ShowError
import com.bbrustol.features.ShowGenericError
import com.bbrustol.features.home.HeadlineViewModel.UiState.Failure
import com.bbrustol.uikit.R as UIKIT_R
@Composable
fun ShowFailure(uiState: Failure, onRetryAction: () -> Unit) {
    if (uiState.code == NO_API_KEY) {
        ShowGenericError(stringResource(UIKIT_R.string.no_token), onRetryAction)
    } else {
        ShowError(uiState.code, uiState.message, onRetryAction)
    }
}
