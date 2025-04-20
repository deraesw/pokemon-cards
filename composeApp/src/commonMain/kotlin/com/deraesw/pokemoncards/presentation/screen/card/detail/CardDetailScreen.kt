package com.deraesw.pokemoncards.presentation.screen.card.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SheetValue
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.deraesw.pokemoncards.presentation.compose.images.PcsImage
import com.deraesw.pokemoncards.presentation.theme.colorCardType
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardDetailScreen(
    cardId: String,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    cardDetailViewModel: CardDetailViewModel = koinInject(
        parameters = { parametersOf(cardId) }
    ),
) {
    val uiState by cardDetailViewModel.uiState.collectAsStateWithLifecycle()
    uiState?.let { detail ->
        val mainColor = remember { colorCardType(detail.mainType) }
        val scaffoldState = rememberBottomSheetScaffoldState(
            bottomSheetState = rememberStandardBottomSheetState(
                initialValue = SheetValue.PartiallyExpanded
            )
        )

        BottomSheetScaffold(
            topBar = {
                TopAppBar(
                    navigationIcon = {
                        IconButton(
                            onClick = onNavigateBack
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                contentDescription = ""
                            )
                        }
                    },
                    title = {},
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = mainColor,
//                        titleContentColor = Color.White,
//                        navigationIconContentColor = Color.White,
//                        actionIconContentColor = Color.White
                    ),
                )
            },
            sheetPeekHeight = 200.dp,
            scaffoldState = scaffoldState,
            sheetContent = {
                CardInformationSection(
                    cardDetail = detail,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .navigationBarsPadding()
                )
            },
            containerColor = mainColor,
        ) { innerPadding ->
            Box(
                modifier = modifier
                    .padding(innerPadding)
                    .background(colorCardType(detail.mainType))
            ) {
                CardImage(
                    type = detail.mainType,
                    imageUrl = detail.imageLarge,
                )
            }
        }
    }
}

@Composable
private fun CardImage(
    type: String,
    imageUrl: String?,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(colorCardType(type))
            .padding(horizontal = 16.dp)
    ) {
        if (imageUrl != null) {
            PcsImage(
                imageUrl = imageUrl,
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
            )
        } else {
            // TODO - Add placeholder
        }
    }
}
