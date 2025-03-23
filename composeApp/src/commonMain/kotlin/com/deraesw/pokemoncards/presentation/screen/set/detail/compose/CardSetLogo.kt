package com.deraesw.pokemoncards.presentation.screen.set.detail.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage

@Composable
fun CardSetLogo(
    logoUrl: String?,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
    ) {
        CoilImage(
            imageModel = { logoUrl },
            imageOptions = ImageOptions(
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
            ),
            loading = @Composable {
                Box(modifier = Modifier.size(48.dp)) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            },
        )
    }
}
