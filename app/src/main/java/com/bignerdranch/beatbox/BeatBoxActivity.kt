package com.bignerdranch.beatbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

class BeatBoxActivity : SingleFragmentActivity() {
    override fun createFragment() = BeatBoxFragment.newInstance()

}