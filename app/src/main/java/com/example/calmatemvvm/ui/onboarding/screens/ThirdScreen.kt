package com.example.calmatemvvm.ui.onboarding.screens

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.calmatemvvm.R
import com.example.calmatemvvm.databinding.FragmentThirdScreenBinding
import com.example.calmatemvvm.ui.common.BaseFragment
import com.example.calmatemvvm.ui.common.BaseViewModel

class ThirdScreen(override val viewModel: BaseViewModel) : BaseFragment<FragmentThirdScreenBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.next3.setOnClickListener {
            appViewModel.navigationUnit.navigate(
                R.id.welcomeFragment,
                null
            )
            onBoardingFinished()
        }
    }

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentThirdScreenBinding {
        return FragmentThirdScreenBinding.inflate(inflater)
    }

    private fun onBoardingFinished() {
        val sharedPreferences = requireContext().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("Finished", true)
        editor.apply()
    }
}