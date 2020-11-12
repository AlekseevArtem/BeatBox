package com.bignerdranch.beatbox

data class Sound(val assetPath: String) {
    val title: String = assetPath.split("/").let { it[it.size - 1].replace(".wav", "") }
}