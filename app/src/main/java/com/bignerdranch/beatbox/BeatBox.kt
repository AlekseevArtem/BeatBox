package com.bignerdranch.beatbox

import android.content.Context
import android.content.res.AssetManager
import android.util.Log

class BeatBox(private val mAssets: AssetManager) {
    val mSounds = mutableListOf<Sound>()
    companion object {
        const val TAG = "BeatBox"
        const val SOUNDS_FOLDER = "sample_sounds"
    }

    init {
        loadSounds()
    }

    private fun loadSounds() {
        val soundNames = mAssets.list(SOUNDS_FOLDER)
        if (soundNames != null) {
            Log.i(TAG, "Found ${soundNames.size} sounds")
        } else Log.e(TAG, "Could not list assets")
        soundNames?.forEach {
            val sound = Sound("$SOUNDS_FOLDER/$it")
            mSounds.add(sound)
        }
    }
}