package com.megaache.tetris

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.megaache.tetris.core.SoundUtil
import com.megaache.tetris.core.StatusBarUtil
import com.megaache.tetris.ui.presentation.game.GameScreen
import com.megaache.tetris.ui.theme.TetrisTheme
import kotlinx.coroutines.ObsoleteCoroutinesApi

@OptIn(ObsoleteCoroutinesApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.transparentStatusBar(this)
        SoundUtil.init(application)

        setContent {
            TetrisTheme {
                GameScreen()
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        SoundUtil.release()
    }
}


