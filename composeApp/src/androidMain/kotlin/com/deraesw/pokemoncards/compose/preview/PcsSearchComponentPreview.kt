package com.deraesw.pokemoncards.compose.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.deraesw.pokemoncards.presentation.compose.PcsSearchComponent

@Preview(showBackground = true)
@Composable
private fun PcsSearchComponentPreview() {
    PcsSearchComponent(
        onClearQuery = {},
        onQueryChange = {}
    )
}
