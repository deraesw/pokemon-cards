package com.deraesw.pokemoncards.presentation.compose.images

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.IntSize
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage

@Composable
fun PcsImage(
    imageUrl: String,
    contentScale: ContentScale = ContentScale.Crop,
    modifier: Modifier = Modifier,
    requestSize: IntSize = IntSize(-1, -1)
) {
    CoilImage(
        imageModel = { imageUrl },
        imageOptions = ImageOptions(
            contentScale = contentScale,
            alignment = Alignment.Center,
            requestSize = requestSize
        ),
        loading = @Composable {
            Box(modifier = Modifier.matchParentSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        },
        modifier = modifier
    )
}
