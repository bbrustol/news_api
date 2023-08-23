package com.bbrustol.uikit.compose.scaffold

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String) {
    TopAppBar(
        title = {
            Text(text = title)
        }
    )
}

@Preview
@Composable
fun TopBarDrawerPreview() {
    TopBar("My Title")
}
