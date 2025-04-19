package com.deraesw.pokemoncards.presentation.screen.set.detail.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.vectorResource
import pokemoncards.composeapp.generated.resources.Res
import pokemoncards.composeapp.generated.resources.window_24px

@Composable
fun CardSetViewCardButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black,
            contentColor = Color.White
        ),
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Icon(
                imageVector = vectorResource(Res.drawable.window_24px),
                contentDescription = "Grid cards icon",
                tint = Color.White,
                modifier = Modifier.size(32.dp)
            )
            Text(
                text = "View all cards",
                color = Color.White
            )
        }
    }
}
