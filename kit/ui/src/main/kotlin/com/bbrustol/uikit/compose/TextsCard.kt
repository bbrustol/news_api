package com.bbrustol.uikit.compose

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun TextsCard(
    modifier: Modifier = Modifier,
    content: String,
    maxLines: Int = 3,
    textAlign: TextAlign = TextAlign.Justify,
    style: SpanStyle = SpanStyle(
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onPrimaryContainer,
        fontSize = 12.sp,
    ),
    ) {
    Text(
        buildAnnotatedString {
            withStyle(style = style) { append(content) }
        },
        modifier = modifier,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
        textAlign = textAlign
    )
}

@Preview(showBackground = true, backgroundColor = 0xffffff)
@Composable
fun TextsCardPreview() {
    TextsCard(content = "Lorem ipsum dolor it")
}