package ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage

@Composable
fun LoadAsyncImage(randomImageUrl: String) {
    AsyncImage(
        model = randomImageUrl,
        contentScale = ContentScale.Crop,
        contentDescription = "Dog photo",
        modifier = Modifier.fillMaxWidth().wrapContentHeight()
    )
}