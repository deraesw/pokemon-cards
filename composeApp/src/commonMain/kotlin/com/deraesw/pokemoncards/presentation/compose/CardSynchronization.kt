package com.deraesw.pokemoncards.presentation.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter.Companion.tint
import androidx.compose.ui.unit.dp
import com.deraesw.pokemoncards.presentation.screen.card.list.SyncState
import com.deraesw.pokemoncards.presentation.theme.ColorPalette
import com.deraesw.pokemoncards.presentation.theme.PokemonCardTheme
import org.jetbrains.compose.resources.stringResource
import pokemoncards.composeapp.generated.resources.Res
import pokemoncards.composeapp.generated.resources.sync_complete
import pokemoncards.composeapp.generated.resources.sync_in_progress

@Composable
fun CardSynchronization(
    syncType: SyncState.Type,
    message: String,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {
    if (syncType == SyncState.Type.NONE) return
    val title = remember(syncType) {
        if (syncType == SyncState.Type.COMPLETE) {
            Res.string.sync_complete
        } else {
            Res.string.sync_in_progress
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {

        Text(
            text = stringResource(title),
            style = PokemonCardTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (syncType != SyncState.Type.COMPLETE) {
            LinearProgressIndicator()
            if (message.isNotEmpty()) {
                Text(
                    text = message,
                    style = PokemonCardTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
            }
            TextButton(
                onClick = onDismiss
            ) {
                Text(text = "Cancel")
            }
        } else {
            Box(
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .size(32.dp)
                    .clip(RoundedCornerShape(50f))
                    .background(ColorPalette.Green300)
            ) {
                Image(
                    Icons.Default.Check,
                    contentDescription = "",
                    modifier = Modifier.matchParentSize(),
                    colorFilter = tint(Color.White)
                )
            }
            TextButton(
                onClick = onDismiss
            ) {
                Text(text = "Ok")
            }
        }
    }
}
