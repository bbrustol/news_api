package com.bbrustol.features.home.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.bbrustol.features.IsLoading
import com.bbrustol.features.ShowGenericError
import com.bbrustol.features.home.HeadlineShareViewModel
import com.bbrustol.features.home.HeadlineViewModel
import com.bbrustol.features.home.HeadlineViewModel.UiState
import com.bbrustol.features.home.HeadlineViewModel.UiState.*
import com.bbrustol.uikit.R as UIKIT_R
@Composable
fun GetHeadline(
    navController: NavHostController,
    viewModel: HeadlineViewModel,
    headlineShareViewModel: HeadlineShareViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    HeadlineRenderState(
        uiState = uiState,
        navController,
        viewModel::fetchHeadline,
        headlineShareViewModel
    )
}

@Composable
fun HeadlineRenderState(
    uiState: UiState,
    navController: NavHostController,
    onRetryAction: () -> Unit,
    headlineShareViewModel: HeadlineShareViewModel
) {
    when (uiState) {
        Idle -> { /*nothing to do*/ }
        Loading -> IsLoading()
        is Failure -> ShowFailure(uiState, onRetryAction)
        is Catch -> ShowGenericError(
            uiState.message ?: stringResource(UIKIT_R.string.catch_generic_message),
            onRetryAction
        )
        is Success -> HeadlineScreen(uiState.headlineModel, headlineShareViewModel, navController)
    }
}