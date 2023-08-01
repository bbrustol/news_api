package com.bbrustol.uikit.compose.scaffold

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.bbrustol.uikit.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    onOpenDrawer: () -> Unit,
) {
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.app_name))
        },
        actions = {
            IconButton(onClick = onOpenDrawer) {
                Icon(
                    painter = painterResource(id = R.drawable.filter_list),
                    contentDescription = stringResource(R.string.speech_filter_topbar),
                )
            }
        },
    )
}

@Preview
@Composable
fun TopBarPreview() {
    TopBar() {}
}