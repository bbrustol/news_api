package com.bbrustol.features.ui.home.compose

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bbrustol.features.home.compose.utils.HeadlineListPreviewParamProvider
import com.bbrustol.features.home.compose.utils.HeadlinePreviewParamProvider
import com.bbrustol.domain.model.HeadlineModel
import com.bbrustol.uikit.extensions.formatDate
import com.bbrustol.uikit.extensions.getHeight
import com.bbrustol.uikit.utils.LoadImage
import com.bbrustol.uikit.utils.TimeFormatType
import com.bbrustol.uikit.R as UIKIT_R

@Composable
fun HeadlineScreen(headlineModel: List<HeadlineModel>, navController: NavHostController) {
    val state = rememberLazyListState()
    LazyColumn(state = state) {
        items(headlineModel.size) {
            CardHeadline(headlineModel[it], it, navController)
        }
    }
}

@Composable
fun DetailsScreen(
    headlineModel: HeadlineModel,
    modifier: Modifier = Modifier,
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val requester = FocusRequester()

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                requester.requestFocus()
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
    }
    with(headlineModel) {
        val context = LocalContext.current
        val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(url)) }

        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            LoadImage(
                imageUrl = urlToImage,
                contentDescription = null,
                placeholder = UIKIT_R.drawable.newspaper,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(LocalConfiguration.current.getHeight(2))
                    .clip(RoundedCornerShape(dimensionResource(id = UIKIT_R.dimen.margin_normal))),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier.padding(
                    top = 0.dp,
                    start = dimensionResource(id = UIKIT_R.dimen.margin_double),
                    end = dimensionResource(id = UIKIT_R.dimen.margin_double),
                    bottom = dimensionResource(id = UIKIT_R.dimen.margin_double)
                )

            ) {

                ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                    val (author, publishedAt) = createRefs()

                    Text(
                        headlineModel.author,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.constrainAs(author) {
                            top.linkTo(parent.top, margin = 8.dp)
                        }
                    )
                    Text(
                        headlineModel.publishedAt.formatDate(TimeFormatType.DDMMMYYYY_HHMM.pattern),
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.constrainAs(publishedAt) {
                            top.linkTo(author.top)
                            linkTo(author.end, parent.end, bias = 1F)
                            width = Dimension.preferredWrapContent
                        }
                    )
                }

                Spacer(modifier = Modifier.height(dimensionResource(id = UIKIT_R.dimen.margin_double)))

                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier
                        .semantics { heading() }
                        .focusRequester(requester)
                        .focusable()
                )

                Spacer(modifier = Modifier.height(dimensionResource(id = UIKIT_R.dimen.margin_double)))

                Text(
                    text = description,
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(dimensionResource(id = UIKIT_R.dimen.margin_double)))

                if (!content.isNullOrEmpty()) {
                    Text(
                        text = content!!,
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Spacer(modifier = Modifier.height(dimensionResource(id = UIKIT_R.dimen.margin_normal)))
                }

                OutlinedButton(
                    onClick = { context.startActivity(intent) },
                    shape = RoundedCornerShape(5.dp),
                    border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
                ) {
                    Text(
                        text = stringResource(id = UIKIT_R.string.details_screen_read_more),
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xffffff)
@Composable
fun HeadlineScreenPreview(
    @PreviewParameter(HeadlineListPreviewParamProvider::class) headlineModel: List<HeadlineModel>
) {
    HeadlineScreen(headlineModel, rememberNavController())
}


@Preview(showBackground = true, backgroundColor = 0xffffff)
@Composable
fun HeadlineDetailsScreenPreview(
    @PreviewParameter(HeadlinePreviewParamProvider::class) headlineModel: HeadlineModel
) {
    DetailsScreen(headlineModel)
}
