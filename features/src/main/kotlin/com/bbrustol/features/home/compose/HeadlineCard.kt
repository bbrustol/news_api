package com.bbrustol.features.home.compose

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bbrustol.features.home.compose.utils.HeadlinePreviewParamProvider
import com.bbrustol.features.home.model.HeadlineModel
import com.bbrustol.uikit.compose.TextsCard
import com.bbrustol.uikit.extensions.formatDate
import com.bbrustol.uikit.utils.LoadImage
import com.bbrustol.uikit.utils.NavScreensType
import com.bbrustol.uikit.utils.TimeFormatType
import java.util.*
import com.bbrustol.uikit.R as UIKIT_R

@Composable
fun CardHeadline(headlineModel: HeadlineModel, position: Int, navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp, 4.dp, 8.dp, 0.dp)
            .clickable { navController.navigate( NavScreensType.HEADLINE_DETAILS.name + "/${position}") },
        shape = RoundedCornerShape(size = 4.dp),
        colors = cardColors(colorScheme.primaryContainer),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            with(headlineModel) {
                Row {
                    LoadImage(
                        imageUrl = urlToImage,
                        contentDescription = null,
                        placeholder = UIKIT_R.drawable.newspaper,
                        modifier = Modifier
                            .size(100.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .weight(.2f),
                        contentScale = ContentScale.Crop
                    )

                    Column(
                        modifier = Modifier
                            .padding(4.dp, 0.dp)
                            .align(Alignment.CenterVertically)
                            .weight(.6f)
                    ) {
                        TextsCard(content = headlineModel.title)

                        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                            val (author, publishedAt) = createRefs()

                            TextsCard(
                                content = headlineModel.author,
                                modifier = Modifier.constrainAs(author) {
                                    top.linkTo(parent.top, margin = 16.dp)
                                }
                            )
                            TextsCard(
                                content = headlineModel.publishedAt.formatDate(TimeFormatType.DDMMMYYYY_HHMM.pattern),
                                modifier = Modifier.constrainAs(publishedAt) {
                                    top.linkTo(author.top)
                                    linkTo(author.end, parent.end, bias = 1F)
                                    width = Dimension.preferredWrapContent
                                }
                            )
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
    @PreviewParameter(HeadlinePreviewParamProvider::class) headlineModel: HeadlineModel
) {
    CardHeadline(headlineModel, 0, rememberNavController())
}
