package com.bbrustol.newsapi

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

import com.bbrustol.features.home.*
import com.bbrustol.features.home.HomeViewModel.*
import com.bbrustol.features.home.HomeViewModel.UiState.*
import com.bbrustol.features.home.compose.EmptyStateScreen
import com.bbrustol.features.home.model.HeadlineModel
import com.bbrustol.uikit.compose.scaffold.TopBar
import com.bbrustol.uikit.theme.NewsApiTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import com.bbrustol.uikit.R as UIKIT_R

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { Start() }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Start(viewModel: HomeViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    RenderState(uiState = uiState, viewModel::fetchHeadline)
}

@Composable
fun RenderState(uiState: UiState, onRetryAction: () -> Unit) {
    when (uiState) {
        Idle -> {/*nothing to do*/}
        Loading -> IsLoading()
        is Failure -> ShowError(uiState.code, uiState.message, onRetryAction)
        is Catch -> ShowGenericError(
            uiState.message ?: stringResource(UIKIT_R.string.catch_generic_message),
            onRetryAction
        )
        is Success -> SetupView(uiState.headlineModel)
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SetupView(headlineModel: List<HeadlineModel>) {
    NewsApiTheme {
        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val scope = rememberCoroutineScope()

        Scaffold(
            topBar = {
                TopBar(headlineModel[0].sourceName, onOpenDrawer = {
                    scope.launch {
                        with(drawerState) {
                            if (isClosed) open() else close()
                        }
                    }
                })
            },
        ) {
            SetDrawer(drawerState, headlineModel)
        }
    }
}

@Composable
private fun SetDrawer(
    drawerState: DrawerState,
    headlineModel: List<HeadlineModel>
) {

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Column(modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Spacer(Modifier.height(78.dp))

                }
            }
        },
        content = { if (headlineModel.isNotEmpty()) HeadlineScreen(headlineModel) else EmptyStateScreen() }
    )
}
