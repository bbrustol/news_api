package com.bbrustol.newsapi.compose

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.bbrustol.features.home.*
import com.bbrustol.features.home.HeadlineViewModel.*
import com.bbrustol.features.home.HeadlineViewModel.UiState.*
import com.bbrustol.uikit.compose.scaffold.TopBar
import com.bbrustol.uikit.theme.NewsApiTheme
import com.bbrustol.uikit.R as UIKIT_R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SetupView(isBiometric: Boolean, topBarTitle: String? = null) {
    val context = LocalContext.current
    NewsApiTheme {
        val navController = rememberNavController()

        Scaffold(
            topBar = {
                TopBar(
                    topBarTitle ?: context.getString(UIKIT_R.string.app_name)
                )
            }
        ) {
            NavGraph(isBiometric, navController)
        }
    }
}
