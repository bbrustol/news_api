package com.bbrustol.features.home.compose.utils

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.bbrustol.domain.model.HeadlineModel

class HeadlinePreviewParamProvider : PreviewParameterProvider<HeadlineModel> {
    override val values: Sequence<HeadlineModel> =
        sequenceOf(getHeadlineModelMock())
}

class HeadlineListPreviewParamProvider : PreviewParameterProvider<List<HeadlineModel>> {
    override val values: Sequence<List<HeadlineModel>> =
        sequenceOf(
            listOf(
                getHeadlineModelMock(),
                getHeadlineModelMock(),
                getHeadlineModelMock(),
                getHeadlineModelMock(),
                getHeadlineModelMock(),
                getHeadlineModelMock(),
                getHeadlineModelMock(),
                getHeadlineModelMock(),
                getHeadlineModelMock(),
                getHeadlineModelMock(),
                getHeadlineModelMock(),
                getHeadlineModelMock()
            )
        )
}

private fun getHeadlineModelMock() = HeadlineModel(
    id = "bbc-news",
    author = "BBC Sport",
    title = "Onana rants at Maguire during Man Utd defeat Onana rants at Maguire during Man Utd defeat",
    description = "New signing Andre Onana shows he will bring more to Manchester United than ball-playing ability during a 3-2 defeat by Borussia Dortmund in Las Vegas.",
    url = "http://www.bbc.co.uk/sport/football/66357692",
    urlToImage = "https://ichef.bbci.co.uk/live-experience/cps/624/cpsprodpb/75A7/production/_130591103_gettyimages-1566021428.jpg",
    publishedAt ="2023-07-31T11:07:26.5610467Z",
    sourceName = "BBC News",
    content = "Andre Onana joined Manchester United from Inter Milan this summer in a deal worth £47m\r\nNew Manchester United goalkeeper Andre Onana showed he will bring more to Erik ten Hag's team than ball-playing… [+3426 chars]",
)
