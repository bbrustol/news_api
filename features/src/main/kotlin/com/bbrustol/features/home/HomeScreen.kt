package com.bbrustol.features.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bbrustol.features.home.compose.CardHeadline
import com.bbrustol.features.home.compose.ErrorScreen
import com.bbrustol.features.home.model.HeadlineModel

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

@Composable
fun HeadlineScreen(headlineModel: List<HeadlineModel>) {
    val state = rememberLazyListState()
    LazyColumn(state = state) {
        items(headlineModel.size) {
            CardHeadline(headlineModel[it])
        }
    }
}