package com.example.calmatemvvm.ui.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.VideoView
import com.example.calmatemvvm.R
import com.example.calmatemvvm.databinding.FragmentHomeBinding
import com.example.calmatemvvm.ui.common.BaseFragment
import com.example.calmatemvvm.common.viewModels
import com.example.calmatemvvm.ui.medication.cardaddmedication.CardAddMedication
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

    private fun getGreetingMessage(): String {
        val c = java.util.Calendar.getInstance()
        val timeOfDay = c.get(java.util.Calendar.HOUR_OF_DAY)
        return when (timeOfDay) {
            in 6..11 -> "Good Morning,"
            in 12..15 -> "Good Afternoon,"
            in 16..20 -> "Good Evening,"
            else -> "Good Night,"
        }
    }
    private fun setHomePageMessages() {
        val greetingMessage = getGreetingMessage()
        val txtGreetingMessage: TextView = binding.cardViewTop.findViewById(R.id.txtHello)
        txtGreetingMessage.text = greetingMessage

        // Set user name on the home page
        val txtUserName: TextView = binding.cardViewTop.findViewById(R.id.txtUserName)
        txtUserName.text = appViewModel.getUser()?.first_name ?: ""
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setToolbarVisibility(false)

        setHomePageMessages()


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

        binding.btnMeditation.setOnClickListener {
            appViewModel.navigationUnit.navigate(
                HomeFragmentDirections.actionHomeFragmentToMeditationListFragment()
            )
            val bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)
            bottomNavigationView?.selectedItemId = R.id.page_meditation
        }

        binding.btnPositive.setOnClickListener {
            appViewModel.navigationUnit.navigate(
                R.id.positiveFragment,
                null
            )
        }

        binding.btnMedications.setOnClickListener{
            appViewModel.navigationUnit.navigate(
                R.id.addMedication,
                null
            )

        }

        binding.buttonMood.setOnClickListener {
            appViewModel.navigationUnit.navigate(
                R.id.askMoodFragment,
                null
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

        val childAddMedicationFragment = CardAddMedication()
        childFragmentManager.beginTransaction()
            .replace(R.id.card_view_add_medication, childAddMedicationFragment)
            .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


}