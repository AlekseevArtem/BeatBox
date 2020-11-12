package com.bignerdranch.beatbox

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

class SoundViewModel(beatBox: BeatBox) : BaseObservable() {
    var sound: Sound? = null
        set(value) {
            field = value
            notifyChange()
        }
    val beatBox: BeatBox = beatBox
    val title: String
        @Bindable get() = sound?.title ?: "No file"

    fun onButtonClicked() = sound?.let { beatBox.play(it) }
}