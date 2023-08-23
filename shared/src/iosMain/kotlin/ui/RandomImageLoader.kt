package ui

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale

@Composable
fun LoadAsyncImage(imageBitmap: ImageBitmap?) {
    imageBitmap?.let { pic ->
        val bitmapState: ImageBitmap? by remember { mutableStateOf(pic) }
        bitmapState?.let { bitmap ->
            Image(
                bitmap = bitmap,
                contentDescription = "",
                contentScale = ContentScale.FillWidth,
                alpha = DefaultAlpha,
                colorFilter = null,
            )
        }
    }
}