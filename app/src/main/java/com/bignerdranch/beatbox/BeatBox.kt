package com.bignerdranch.beatbox

import android.annotation.TargetApi
import android.content.res.AssetManager
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import android.util.Log


open class BeatBox(private val assets: AssetManager) {
    val sounds = mutableListOf<Sound>()
    val soundPool = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) oldSoundPool() else newSoundPool()

    companion object {
        const val TAG = "BeatBox"
        const val SOUNDS_FOLDER = "sample_sounds"
        const val MAX_SOUNDS = 5

        @Suppress("DEPRECATION")
        private fun oldSoundPool() = SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC, 0)

        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        private fun newSoundPool() = SoundPool.Builder()
            .setAudioAttributes(
                AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build()
            )
            .build()
    }

    init {
        loadSounds()
    }

    private fun loadSounds() {
        val soundNames = assets.list(SOUNDS_FOLDER)
        if (soundNames != null) {
            Log.i(TAG, "Found ${soundNames.size} sounds")
        } else Log.e(TAG, "Could not list assets")
        soundNames?.forEach {
            val sound = Sound("$SOUNDS_FOLDER/$it")
            load(sound)
            sounds.add(sound)
        }
    }

    private fun load(sound: Sound) {
        val afd = assets.openFd(sound.assetPath)
        val soundId = soundPool.load(afd,1)
        sound.soundId = soundId
    }

    open fun play(sound: Sound) {
        sound.soundId?.let { soundPool.play(it, 1.0f,1.0f,1,0,1.0f) }
    }

    open fun release() {
        soundPool.release()
    }
}