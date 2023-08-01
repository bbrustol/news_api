package com.bbrustol.features.home.compose

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.bbrustol.features.home.compose.utils.LaunchPreviewParamProvider
import com.bbrustol.features.home.model.HeadlineModel
import com.bbrustol.uikit.compose.TextsCard
import com.bbrustol.uikit.utils.LoadImage
import java.util.*
import com.bbrustol.uikit.R as UIKIT_R

@Composable
fun CardHeadline(headlineModel: HeadlineModel) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp, 4.dp, 8.dp, 0.dp)
            .clickable { },
        shape = RoundedCornerShape(size = 4.dp),
        colors = cardColors(colorScheme.primaryContainer),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .clickable {
                    Toast
                        .makeText(context, "AQUI VAI ABRIR ITEM DE DETALHES", Toast.LENGTH_SHORT)
                        .show()
                }
        ) {
            with(headlineModel) {
                Row {
                    LoadImage(
                        imageUrl = urlToImage,
                        contentDescription = null,
                        placeholder = UIKIT_R.drawable.newspaper,
                        modifier = Modifier
                            .size(80.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .weight(.2f),
                        contentScale = ContentScale.FillBounds
                    )

                    Column(
                        modifier = Modifier
                            .padding(4.dp, 0.dp)
                            .align(Alignment.CenterVertically)
                            .weight(.6f)
                    ) {
                        TextsCard(headlineModel.title)

                        Row (modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                        ) {
                            TextsCard(headlineModel.author)
                            TextsCard(headlineModel.publishedAt)
                        }

                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun HeadlineCardPreview(
    @PreviewParameter(LaunchPreviewParamProvider::class) headlineModel: HeadlineModel
) {
    CardHeadline(headlineModel)
}