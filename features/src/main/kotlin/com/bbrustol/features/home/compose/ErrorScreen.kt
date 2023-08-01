package com.bbrustol.features.home.compose

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bbrustol.uikit.R as UIKIT_R
import com.bbrustol.uikit.theme.NewsApiTheme

@Composable
fun ErrorScreen(message: String, onRetryAction:() -> Unit) {
    Log.d("UI", "Error - $message")
    Column(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(com.bbrustol.uikit.R.drawable.error),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
            )
        Column(modifier = Modifier
            .fillMaxWidth().padding(vertical = 80.dp)
            ) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                text = stringResource(UIKIT_R.string.ops)
            )

            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                text = message
            )
        }

        Row(modifier = Modifier.fillMaxSize()) {
            Button(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp, 8.dp, 8.dp, 0.dp)
                .align(Alignment.Bottom),
                onClick = { onRetryAction() }
            ) {
                Text(
                    stringResource(UIKIT_R.string.try_again),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onError,
                    modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xffffff)
@Composable
fun ErrorScreenPreview() {
    NewsApiTheme {
        ErrorScreen("Lorem ipsum dolor it") {}
    }
}
