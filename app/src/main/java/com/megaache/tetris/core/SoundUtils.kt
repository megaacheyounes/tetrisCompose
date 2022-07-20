package com.megaache.tetris.core

import android.app.Application
import android.media.AudioManager
import android.media.SoundPool
import com.megaache.tetris.R


object SoundUtil {


    private var app: Application? = null
    private val sp: SoundPool by lazy {
        SoundPool.Builder().setMaxStreams(4).setMaxStreams(AudioManager.STREAM_MUSIC).build()
    }
    private val map = mutableMapOf<SoundType, Int>()

    @Deprecated("no longer needed!")
    private var lastSoundId: Int? = null

    fun init(context: Application) {
        app = context
        Sounds.forEach {
            map[it] = sp.load(app, it.res, 1)
        }
    }

    fun release() {
        app = null
        sp.release()
    }


    fun play(isMute: Boolean, sound: SoundType) {
        if (!isMute) {
//            lastSoundId?.let {
//                sp.stop(it)
//            }
            lastSoundId = sp.play(
                requireNotNull(map[sound]),
                1f,
                1f,
                0,
                0,
                1f
            )
        }
    }

}

sealed class SoundType(val res: Int) {
    object Move : SoundType(R.raw.move)
    object Rotate : SoundType(R.raw.rotate)
    object Start : SoundType(R.raw.start)
    object Drop : SoundType(R.raw.drop)
    object Clean : SoundType(R.raw.clean)
}

val Sounds =
    listOf(SoundType.Move, SoundType.Rotate, SoundType.Start, SoundType.Drop, SoundType.Clean)