package com.bignerdranch.beatbox

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.beatbox.databinding.FragmentBeatBoxBinding
import com.bignerdranch.beatbox.databinding.ListItemSoundBinding

class BeatBoxFragment : Fragment() {
    private lateinit var mBeatBox: BeatBox

    companion object {
        fun newInstance() = BeatBoxFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        mBeatBox = BeatBox(activity!!.assets)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentBeatBoxBinding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_beat_box, container, false)
        binding.recyclerView.layoutManager = GridLayoutManager(activity, 3)
        binding.recyclerView.adapter = SoundAdapter(mBeatBox.sounds)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        mBeatBox.release()
    }

    private inner class SoundHolder(val mBinding: ListItemSoundBinding) : RecyclerView.ViewHolder(mBinding.root) {
        init{
            mBinding.viewModel = SoundViewModel(mBeatBox)
        }

        fun bind(sound: Sound) {
            mBinding.viewModel?.sound = sound
            mBinding.executePendingBindings()
        }
    }

    private inner class SoundAdapter(private val mSounds: List<Sound>) : RecyclerView.Adapter<SoundHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoundHolder =
            SoundHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.list_item_sound,
                    parent,
                    false
                )
            )

        override fun onBindViewHolder(holder: SoundHolder, position: Int) =
            holder.bind(mSounds[position])

        override fun getItemCount(): Int = mSounds.size
    }
}