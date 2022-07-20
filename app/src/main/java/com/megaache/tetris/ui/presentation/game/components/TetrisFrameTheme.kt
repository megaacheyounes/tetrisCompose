package com.megaache.tetris.ui.presentation.game.components

import androidx.compose.ui.graphics.Color
import com.megaache.tetris.ui.theme.*

data class TetrisFrameTheme(
    val BodyColorLight: Color,
    val BodyColorDark: Color,
    val ButtonLight: Color,
    val ButtonDark: Color
) {
    companion object {
        val THEMES = listOf(
            TetrisFrameTheme(
                BodyColorLight = Theme0BodyColorLight,
                BodyColorDark = Theme0BodyColorDark,
                ButtonLight = Theme0ButtonLight,
                ButtonDark = Theme0ButtonDark,
            ), TetrisFrameTheme(
                BodyColorLight = Theme1BodyColorLight,
                BodyColorDark = Theme1BodyColorDark,
                ButtonLight = Theme1ButtonLight,
                ButtonDark = Theme1ButtonDark,
            ),
            TetrisFrameTheme(
                BodyColorLight = Theme2BodyColorLight,
                BodyColorDark = Theme2BodyColorDark,
                ButtonLight = Theme2ButtonLight,
                ButtonDark = Theme2ButtonDark,
            ),
            TetrisFrameTheme(
                BodyColorLight = Theme3BodyColorLight,
                BodyColorDark = Theme3BodyColorDark,
                ButtonLight = Theme3ButtonLight,
                ButtonDark = Theme3ButtonDark,
            ),
            TetrisFrameTheme(
                BodyColorLight = Theme4BodyColorLight,
                BodyColorDark = Theme4BodyColorDark,
                ButtonLight = Theme4ButtonLight,
                ButtonDark = Theme4ButtonDark,
            ),
            TetrisFrameTheme(
                BodyColorLight = Theme5BodyColorLight,
                BodyColorDark = Theme5BodyColorDark,
                ButtonLight = Theme5ButtonLight,
                ButtonDark = Theme5ButtonDark,
            ),
            TetrisFrameTheme(
                BodyColorLight = Theme6BodyColorLight,
                BodyColorDark = Theme6BodyColorDark,
                ButtonLight = Theme6ButtonLight,
                ButtonDark = Theme6ButtonDark,
            ),
            TetrisFrameTheme(
                BodyColorLight = Theme7BodyColorLight,
                BodyColorDark = Theme7BodyColorDark,
                ButtonLight = Theme7ButtonLight,
                ButtonDark = Theme7ButtonDark,
            )
        )

        fun default(): TetrisFrameTheme = THEMES[0]
        fun random(): TetrisFrameTheme = THEMES.random()
    }

}