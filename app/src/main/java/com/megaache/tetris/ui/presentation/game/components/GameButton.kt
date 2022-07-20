@file:OptIn(ObsoleteCoroutinesApi::class)

package com.megaache.tetris.ui.presentation.game.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.megaache.tetris.ui.presentation.game.LocalTheme
import kotlinx.coroutines.ObsoleteCoroutinesApi


@Composable
fun GameButton(
    modifier: Modifier = Modifier,
    size: Dp,
    onClick: () -> Unit = {},
    content: @Composable (Modifier) -> Unit = {}
) {
    val theme = LocalTheme.current

    val backgroundShape = RoundedCornerShape(size / 2)

    Box(
        modifier = modifier
            .shadow(5.dp, shape = backgroundShape)
            .size(size = size)
            .clip(backgroundShape)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        theme.ButtonLight,
                        theme.ButtonDark
                    ),
                    startY = 0f,
                    endY = 80f
                )
            )
            .clickable { onClick() }
    ) {
        content(Modifier.align(Alignment.Center))
    }
}
