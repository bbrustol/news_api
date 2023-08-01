package com.bbrustol.features.home.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.bbrustol.uikit.R as UIKIT_R
import com.bbrustol.uikit.theme.NewsApiTheme

@Composable
fun EmptyStateScreen() {
    Column(modifier = Modifier.fillMaxSize()
        .background(Color.LightGray.copy(0.3f)),
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(UIKIT_R.drawable.empty_state),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(),
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xffffff)
@Composable
fun EmptyStateScreenPreview() {
    NewsApiTheme {
        EmptyStateScreen()
    }
}
