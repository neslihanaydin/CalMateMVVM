package com.example.calmatemvvm.ui.meditation

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.navigation.fragment.navArgs
import com.example.calmatemvvm.R
import com.example.calmatemvvm.databinding.FragmentMeditationBinding
import com.example.calmatemvvm.ui.common.BaseFragment
import com.example.calmatemvvm.common.viewModels
import com.example.calmatemvvm.common.viewScope
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask
import java.util.concurrent.TimeUnit

class MeditationFragment : BaseFragment<FragmentMeditationBinding>() {

    private lateinit var mediaPlayer: MediaPlayer
    private val args: MeditationFragmentArgs by navArgs()
    private var timer: Timer = Timer()
    override val viewModel by viewModels {
        MeditationViewModel()
    }

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentMeditationBinding {

        // Bottom Navigation Bar Invisible
        val bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView?.visibility = View.GONE

        return FragmentMeditationBinding.inflate(inflater).also {
            it.viewModel = viewModel
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMediaPlayer()
        initViews()
        setClickListeners()
        binding.itemThumbnail.setImageResource(args.imgSource)
    }

    private fun setClickListeners() {
        with(binding) {
            btnPlay.setOnClickListener {
                mediaPlayer.start()
                btnPause.visibility = View.VISIBLE
                btnPlay.visibility = View.GONE
            }

            btnPause.setOnClickListener {
                mediaPlayer.pause()
                btnPause.visibility = View.GONE
                btnPlay.visibility = View.VISIBLE
            }

            textViewBack.setOnClickListener {
                mediaPlayer.release()
                timer.cancel()
                appViewModel.navigationUnit.navigateBack()
            }
        }
    }

    override fun onDestroy() {
        mediaPlayer.release()
        super.onDestroy()
    }

    private fun initMediaPlayer() {
        mediaPlayer = MediaPlayer.create(requireContext(), args.audioSource)
        mediaPlayer.setOnPreparedListener { mp ->
            binding.seekBar.max = mp.duration
            binding.tvTotalTime.text = formatTime(mp.duration)
            timer.scheduleAtFixedRate(object : TimerTask() {
                override fun run() {
                    viewScope.launch {
                        binding.seekBar.progress = mp.currentPosition
                        binding.tvCurrentTime.text = formatTime(mp.currentPosition)
                    }
                }
            }, 0, 1000)
        }
    }

    private fun formatTime(duration: Int): String {
        val minutes = TimeUnit.MILLISECONDS.toMinutes(duration.toLong())
        val seconds = TimeUnit.MILLISECONDS.toSeconds(duration.toLong()) % 60
        return String.format("%02d:%02d", minutes, seconds)
    }

    private fun initViews() {
        with(binding) {
            seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {}

                override fun onStartTrackingTouch(seekBar: SeekBar?) {}

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                    mediaPlayer.seekTo(seekBar?.progress ?: 0)
                }
            })
        }
    }
}