package com.megaache.tetris.ui.presentation.game.components

import android.annotation.SuppressLint
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.megaache.tetris.core.LedFontFamily
import com.megaache.tetris.ui.theme.BrickMatrix
import com.megaache.tetris.ui.theme.BrickSpirit
import com.megaache.tetris.ui.theme.Dimensions
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt


@Composable
fun LedClock(modifier: Modifier = Modifier) {

    val config = LocalConfiguration.current
    val dimens = if (config.screenWidthDp >= 700)
        Dimensions.TabletDimensions
    else
        Dimensions.PhoneDimensions

    val textSize = dimens.baseFontSize.times(1.15f)
    val textWidth = dimens.innerPadding.times(1.33f)

    val animateValue by rememberInfiniteTransition().animateFloat(
        initialValue = 0f, targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ),
    )

    var clock by remember { mutableStateOf(0 to 0) }

    DisposableEffect(key1 = animateValue.roundToInt()) {
        @SuppressLint("SimpleDateFormat")
        val dateFormat: DateFormat = SimpleDateFormat("H,m")
        val (curHou, curMin) = dateFormat.format(Date()).split(",")
        clock = curHou.toInt() to curMin.toInt()
        onDispose { }
    }

    Row(modifier) {
        LedNumber(
            num = clock.first,
            digits = 2,
            fillZero = true,
            dimens = dimens
        )

        val ledComma: @Composable (color: Color) -> Unit = remember {
            {
                Text(
                    ":",
                    fontFamily = LedFontFamily,
                    textAlign = TextAlign.End,
                    color = it,
                    fontSize = textSize,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Box(
            modifier = Modifier
                .width(dimens.innerPadding)
                .padding(end = 1.dp),
        ) {

            ledComma(BrickMatrix)
            if (animateValue.roundToInt() == 1) {
                ledComma(BrickSpirit)
            }

        }

        LedNumber(
            num = clock.second,
            digits = 2,
            fillZero = true,
            dimens = dimens

        )
    }

}

@Composable
fun LedNumber(
    modifier: Modifier = Modifier,
    num: Int,
    digits: Int,
    fillZero: Boolean = false,
    dimens:Dimensions
) {
    val textSize = dimens.baseFontSize.times(1.15f)
    val textWidth = dimens.innerPadding.times(1.33f)

    Box(modifier) {
        Row(modifier = Modifier.align(Alignment.CenterEnd)) {
            repeat(digits) {
                Text(
                    "8",
                    fontSize = textSize,
                    color = BrickMatrix,
                    fontFamily = LedFontFamily,
                    modifier = Modifier.width(textWidth),
                    textAlign = TextAlign.End

                )
            }

        }
        Row(
            modifier = Modifier.align(Alignment.CenterEnd),
        ) {
            val str = if (fillZero) String.format("%0${digits}d", num) else num.toString()
            str.iterator().forEach {
                Text(
                    it.toString(),
                    fontSize = textSize,
                    color = BrickSpirit,
                    fontFamily = LedFontFamily,
                    modifier = Modifier.width(textWidth),
                    textAlign = TextAlign.End

                )
            }

        }

    }
}