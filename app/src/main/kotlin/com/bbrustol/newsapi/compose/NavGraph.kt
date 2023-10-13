package com.bbrustol.newsapi.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bbrustol.features.home.HeadlineShareViewModel
import com.bbrustol.features.home.HeadlineViewModel
import com.bbrustol.features.home.compose.BiometricScreen
import com.bbrustol.features.home.compose.DetailsScreen
import com.bbrustol.features.home.compose.EmptyStateScreen
import com.bbrustol.features.home.compose.GetHeadline
import com.bbrustol.uikit.utils.Screen

@Composable
fun NavGraph(
    isBiometric: Boolean,
    navController: NavHostController
) {
    val headlineShareViewModel: HeadlineShareViewModel = hiltViewModel()

    Column {
        Spacer(modifier = Modifier.height(56.dp))

        NavHost(
            navController = navController,
            startDestination = if (isBiometric) Screen.GoBiometric.route else Screen.GetHeadline.route
        ) {

            composable(Screen.GoBiometric.route) {
                BiometricScreen(navController)
            }

            composable(Screen.GetHeadline.route) {
                val viewModel: HeadlineViewModel = hiltViewModel()
                GetHeadline(navController, viewModel, headlineShareViewModel)
            }

            composable(route = Screen.GoHeadlineDetails.route) {
                if (headlineShareViewModel.headlineModel != null) {
                    DetailsScreen(headlineShareViewModel.headlineModel!!)
                } else {
                    EmptyStateScreen()
                }
            }
        }
    }
}