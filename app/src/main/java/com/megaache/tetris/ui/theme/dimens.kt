package com.megaache.tetris.ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


sealed class Dimensions(
    val screenWidth: Dp,
    val screenHeight: Dp,
    val screenPadding: Dp,

    val settingsButtonSize: Dp,
    val directionButtonSize: Dp,
    val rotateButtonSize: Dp,
    val baseFontSize: TextUnit,
    val topPadding: Dp,
    val mainSpacerHeight: Dp,
    val innerPadding: Dp,
    val roundCornersRadius: Dp,
    val directionBtnContainerHeight: Dp,
    val controlsTopPadding: Dp,
    val borderWidth :Dp,
    val ledTextMargin:Dp,
    val controlsFrameMargin:Dp
) {
    object PhoneDimensions : Dimensions(
        topPadding = 40.dp,
        screenWidth = 300.dp, //260 (0.866% height)
        screenHeight = 340.dp, //300
        screenPadding = 20.dp,
        mainSpacerHeight = 20.dp,

        baseFontSize = 14.sp,
        innerPadding = 6.dp,
        roundCornersRadius = 10.dp,
        directionBtnContainerHeight = 160.dp,
        controlsTopPadding = 40.dp,
        settingsButtonSize = 24.dp,
        directionButtonSize = 60.dp,
        rotateButtonSize = 90.dp,
        borderWidth = 4.dp,
        ledTextMargin = 8.dp,
        controlsFrameMargin = 16.dp
    )

    object TabletDimensions : Dimensions(
        baseFontSize = 18.sp,

        topPadding = 56.dp,
        screenWidth = 440.dp,
        screenHeight = 480.dp,
        screenPadding = 40.dp,
        controlsTopPadding = 56.dp,
        mainSpacerHeight = 38.dp,

        innerPadding = 12.dp,
        roundCornersRadius = 20.dp,
        directionBtnContainerHeight = 256.dp,

        settingsButtonSize = 32.dp,
        directionButtonSize = 96.dp,
        rotateButtonSize = 160.dp,
        borderWidth = 8.dp,
        ledTextMargin =14.dp,
        controlsFrameMargin= 80.dp

    )
}