package com.bbrustol.uikit.utils

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.bbrustol.uikit.R

@Composable
fun LoadImage(
    modifier: Modifier = Modifier,
    imageUrl: String?,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop,
    @DrawableRes placeholder: Int
) {
    if (LocalInspectionMode.current) {
        Image(
            painter = painterResource(id = R.drawable.newspaper),
            contentDescription = null,
            contentScale = contentScale,
            modifier = modifier
        )
        return
    }
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .error(placeholder)
            .build(),
        loading = {
            CircularProgressIndicator()
        },
        modifier = modifier,
        contentScale = contentScale,
        contentDescription = contentDescription
    )
}
