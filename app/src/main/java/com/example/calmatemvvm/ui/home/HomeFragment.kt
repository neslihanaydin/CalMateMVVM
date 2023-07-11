package com.example.calmatemvvm.ui.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.VideoView
import com.example.calmatemvvm.R
import com.example.calmatemvvm.databinding.FragmentHomeBinding
import com.example.calmatemvvm.ui.common.BaseFragment
import com.example.calmatemvvm.common.viewModels
import com.example.calmatemvvm.ui.meditationlist.recommendedcardview.CardView
import com.example.calmatemvvm.ui.setgoal.cardsetgoal.CardSetGoal
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private var selectedImageView: ImageView? = null
    private var defaultScale: Float = 1f

    override val viewModel by viewModels {
        HomeViewModel()
    }

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentHomeBinding {

        // Bottom Navigation Bar Visible
        val bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView?.visibility = View.VISIBLE

        return FragmentHomeBinding.inflate(inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val videoView: VideoView = binding.cardViewTop.findViewById(R.id.videoViewCard)
        val videoPath =
            "android.resource://" + requireContext().packageName + "/" + R.raw.calmate_intro
        videoView.setVideoPath(videoPath)
        videoView.start()

        binding.btnDailyGoal.setOnClickListener {
            appViewModel.navigationUnit.navigate(
                HomeFragmentDirections.actionHomeFragmentToFitnessFragment()
            )
        }


        val childFragment = CardView()
        childFragmentManager.beginTransaction()
            .replace(R.id.card_view_meditations, childFragment)
            .commit()


        val childSetGoalFragment = CardSetGoal()
        childFragmentManager.beginTransaction()
            .replace(R.id.card_view_step, childSetGoalFragment)
            .commit()
    }


}