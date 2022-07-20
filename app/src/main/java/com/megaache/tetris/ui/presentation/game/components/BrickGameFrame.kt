package com.megaache.tetris.ui.presentation.game.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.megaache.tetris.R
import com.megaache.tetris.core.Direction
import com.megaache.tetris.ui.presentation.game.LocalTheme
import com.megaache.tetris.ui.theme.Dimensions
import com.megaache.tetris.ui.theme.ScreenBackground
import com.megaache.tetris.ui.theme.TetrisTheme


@Composable
fun BrickGameFrame(
    clickable: Clickable = combinedClickable(),
    navigateToPlaystore: () -> Unit = {},
    screen: @Composable () -> Unit
) {
    val theme = LocalTheme.current

    val config = LocalConfiguration.current
    val dimens = if (config.screenWidthDp >= 700)
        Dimensions.TabletDimensions
    else
        Dimensions.PhoneDimensions


    Column(
        Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        theme.BodyColorDark,
                        theme.BodyColorLight
                    )
                )
            )
            .background(
                theme.BodyColorDark,
                RoundedCornerShape(dimens.roundCornersRadius)
            )
            .padding(top = dimens.topPadding)
    ) {

        //Screen
        Box(Modifier.align(Alignment.CenterHorizontally)) {


            //screen white border
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(
                        dimens.screenWidth,
                        dimens.screenHeight.times(1.01f)
                    )
                    .border(
                        BorderStroke(
                            width = dimens.borderWidth,
                            color = Color.White
                        ),
                        RectangleShape
                    )

            )


            //screen
            Box(
                Modifier
                    .align(Alignment.Center)
                    .size(
                        dimens.screenWidth,
                        dimens.screenHeight
                    )
                    .padding(
                        start = dimens.screenPadding,
                        end = dimens.screenPadding,
                        top = dimens.screenPadding,
                        bottom = dimens.screenPadding
                    )
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    drawScreenBorder(
                        Offset(0f, 0f),
                        Offset(size.width, 0f),
                        Offset(0f, size.height),
                        Offset(size.width, size.height)
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimens.innerPadding)
                        .background(ScreenBackground)
                ) {
                    screen()
                }
            }
        }

        Spacer(modifier = Modifier.height(dimens.controlsTopPadding))

        val SettingText = @Composable { text: String, modifier: Modifier ->
            Text(
                text, modifier = modifier,
                color = MaterialTheme.colors.onBackground,
                fontSize = dimens.baseFontSize,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold
            )
        }

        //Setting Button
        Column(
            modifier = Modifier
                .padding(
                    horizontal = dimens.controlsFrameMargin
                )
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                //SOUNDS
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    GameButton(
                        modifier = Modifier,
                        onClick = clickable.onMute,
                        size = dimens.settingsButtonSize
                    ) {}
                    Spacer(modifier = Modifier.height(dimens.innerPadding))
                    SettingText(
                        stringResource(id = R.string.button_sounds),
                        Modifier,
                    )
                }

                //PAUSE
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    GameButton(
                        modifier = Modifier,
                        onClick = clickable.onPause,
                        size = dimens.settingsButtonSize
                    ) {}
                    Spacer(modifier = Modifier.height(dimens.innerPadding))
                    SettingText(
                        stringResource(id = R.string.button_pause),
                        Modifier,
                    )
                }

                //RESET
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    GameButton(
                        modifier = Modifier,
                        onClick = clickable.onRestart,
                        size = dimens.settingsButtonSize
                    ) {}
                    Spacer(modifier = Modifier.height(dimens.innerPadding))
                    SettingText(
                        stringResource(id = R.string.button_reset),
                        Modifier,
                    )
                }

            }

        }


        Spacer(
            modifier = Modifier.height(
                dimens.mainSpacerHeight
            )
        )


        //Game Button
        val ButtonText = @Composable { modifier: Modifier,
                                       text: String ->
            Text(
                text, modifier = modifier,
                color = MaterialTheme.colors.onBackground,
                fontSize = dimens.baseFontSize.times(1.2f)
            )
        }

        Row(
            modifier = Modifier
                .padding(
                    horizontal = dimens.controlsFrameMargin
                )
                .height(dimens.directionBtnContainerHeight)
        ) {
            //DIRECTION BTN
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            ) {
                GameButton(
                    Modifier.align(Alignment.TopCenter),
                    onClick = { clickable.onMove(Direction.Up) },
                    size = dimens.directionButtonSize
                ) {
                    ButtonText(it, stringResource(id = R.string.button_up))
                }
                GameButton(
                    Modifier.align(Alignment.CenterStart),
                    onClick = { clickable.onMove(Direction.Left) },
                    size = dimens.directionButtonSize
                ) {
                    ButtonText(it, stringResource(id = R.string.button_left))
                }
                GameButton(
                    Modifier.align(Alignment.CenterEnd),
                    onClick = {
                        clickable.onMove(Direction.Right)
                    },
                    size = dimens.directionButtonSize
                ) {
                    ButtonText(it, stringResource(id = R.string.button_right))
                }
                GameButton(
                    Modifier.align(Alignment.BottomCenter),
                    onClick = { clickable.onMove(Direction.Down) },
                    size = dimens.directionButtonSize
                ) {
                    ButtonText(it, stringResource(id = R.string.button_down))
                }

            }


            //ROTATE BTN
            Box(
                modifier = Modifier
                    .padding(horizontal = dimens.controlsFrameMargin)
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                GameButton(
                    Modifier.align(Alignment.CenterEnd),
                    onClick = clickable.onRotate,
                    size = dimens.rotateButtonSize
                ) {
                    ButtonText(it, stringResource(id = R.string.button_rotate))
                }
            }
        }

        Spacer(modifier = Modifier.height(dimens.mainSpacerHeight.times(1.3f)))

        //playstore link and theme button
        Row(
            modifier = Modifier
                .padding(start = 40.dp, end = 40.dp)
                .height(dimens.directionBtnContainerHeight)
        ) {
            Column(
                modifier = Modifier.weight(2f)
            ) {}

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                GameButton(
                    modifier = Modifier,
                    onClick = { navigateToPlaystore() },
                    size = dimens.settingsButtonSize
                ) {}
                Spacer(modifier = Modifier.height(dimens.innerPadding))
                SettingText(
                    stringResource(id = R.string.button_playstore),
                    Modifier.clickable { clickable.onChangeTheme }
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                GameButton(
                    modifier = Modifier,
                    onClick = clickable.onChangeTheme,
                    size = dimens.settingsButtonSize
                ) {}
                Spacer(modifier = Modifier.height(dimens.innerPadding))
                SettingText(
                    stringResource(id = R.string.button_theme),
                    Modifier.clickable { clickable.onChangeTheme }
                )
            }
        }

    }
}


fun DrawScope.drawScreenBorder(
    topLef: Offset,
    topRight: Offset,
    bottomLeft: Offset,
    bottomRight: Offset
) {
    var path = Path().apply {
        moveTo(topLef.x, topLef.y)
        lineTo(topRight.x, topRight.y)
        lineTo(
            topRight.x / 2 + topLef.x / 2,
            topLef.y + topRight.x / 2 + topLef.x / 2
        )
        lineTo(
            topRight.x / 2 + topLef.x / 2,
            bottomLeft.y - topRight.x / 2 + topLef.x / 2
        )
        lineTo(bottomLeft.x, bottomLeft.y)
        close()
    }
    drawPath(path, Color.Black.copy(0.5f))

    path = Path().apply {
        moveTo(bottomRight.x, bottomRight.y)
        lineTo(bottomLeft.x, bottomLeft.y)
        lineTo(
            topRight.x / 2 + topLef.x / 2,
            bottomLeft.y - topRight.x / 2 + topLef.x / 2
        )
        lineTo(
            topRight.x / 2 + topLef.x / 2,
            topLef.y + topRight.x / 2 + topLef.x / 2
        )
        lineTo(topRight.x, topRight.y)
        close()
    }

    drawPath(path, Color.White.copy(0.5f))

}


data class Clickable constructor(
    val onMove: (Direction) -> Unit,
    val onRotate: () -> Unit,
    val onRestart: () -> Unit,
    val onPause: () -> Unit,
    val onMute: () -> Unit,
    val onChangeTheme: () -> Unit
)

fun combinedClickable(
    onMove: (Direction) -> Unit = {},
    onRotate: () -> Unit = {},
    onRestart: () -> Unit = {},
    onPause: () -> Unit = {},
    onMute: () -> Unit = {},
    onChangeTheme: () -> Unit = {}
) = Clickable(onMove, onRotate, onRestart, onPause, onMute, onChangeTheme)


@Preview(widthDp = 400, heightDp = 700)
@Composable
fun PreviewGameBody() {
    TetrisTheme {
        BrickGameFrame {}
    }
}

//@Preview(widthDp = 700, heightDp = 1280)
//@Composable
//fun PreviewGameBody_largeScreen() {
//    TetrisTheme {
//        BrickGameFrame {}
//    }
//}


 