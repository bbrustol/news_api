package com.bbrustol.uikit.utils

sealed class Screen (val route: String) {
    data object GoBiometric : Screen ("goBiometric")
    data object GetHeadline : Screen ("getHeadline")
    data object GoHeadlineDetails : Screen ("goHeadlineDetails")
}
