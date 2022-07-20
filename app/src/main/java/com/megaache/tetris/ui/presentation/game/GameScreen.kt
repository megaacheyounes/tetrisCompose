@file:OptIn(ObsoleteCoroutinesApi::class)

package com.megaache.tetris.ui.presentation.game

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.megaache.tetris.core.Direction
import com.megaache.tetris.ui.presentation.game.components.*
import com.megaache.tetris.ui.theme.TetrisTheme
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

val LocalTheme = compositionLocalOf {
    TetrisFrameTheme.default()
}



@Composable
fun GameScreen() {
    val viewModel = viewModel<GameViewModel>()
    val viewState = viewModel.viewState.value

    CompositionLocalProvider(LocalTheme provides viewState.theme) {
        GameScreenContent(viewModel)
    }

}

const val PS_LINK = "https://play.google.com/store/apps/developer?id=Megaache+smart+apps"

@Composable
fun GameScreenContent(
    viewModel: GameViewModel
) {
    val viewState = viewModel.viewState.value

    val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(PS_LINK)) }
    val context = LocalContext.current

    val navigateToPs = {
        context.startActivity(intent)
    }

    // A surface container using the 'background' color from the theme
    Surface(color = MaterialTheme.colors.background) {

        LaunchedEffect(key1 = Unit) {
            while (isActive) {
                delay(viewState.tickDuration)
                viewModel.dispatch(Action.GameTick)
            }
        }

        val lifecycleOwner = LocalLifecycleOwner.current
        DisposableEffect(key1 = Unit) {
            val observer = object : DefaultLifecycleObserver {
                override fun onResume(owner: LifecycleOwner) {
                    viewModel.dispatch(Action.Resume)
                }

                override fun onPause(owner: LifecycleOwner) {
                    viewModel.dispatch(Action.Pause)
                }
            }
            lifecycleOwner.lifecycle.addObserver(observer)
            onDispose {
                lifecycleOwner.lifecycle.removeObserver(observer)
            }
        }



        BrickGameFrame(
            navigateToPlaystore = navigateToPs,
            clickable = combinedClickable(
                onMove = { direction: Direction ->
                     if (direction == Direction.Up) viewModel.dispatch(Action.Drop)
                    else viewModel.dispatch(Action.Move(direction))
                },
                onRotate = {
                    viewModel.dispatch(Action.Rotate)
                },
                onRestart = {
                    viewModel.dispatch(Action.Reset)
                },
                onPause = {
                    if (viewModel.viewState.value.isRunning) {
                        viewModel.dispatch(Action.Pause)
                    } else {
                        viewModel.dispatch(Action.Resume)
                    }
                },
                onMute = {
                    viewModel.dispatch(Action.Mute)
                },
                onChangeTheme = {
                    viewModel.dispatch(Action.ChangeTheme)
                }
            )) {
            GameLEDScreen(
                Modifier.fillMaxSize()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGameScreen() {
    TetrisTheme {
        CompositionLocalProvider(LocalTheme provides TetrisFrameTheme.default()) {
            BrickGameFrame {
                PreviewGameLEDScreen(
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }
    }
}