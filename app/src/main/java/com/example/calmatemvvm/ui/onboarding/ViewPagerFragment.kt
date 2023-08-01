package com.example.calmatemvvm.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.calmatemvvm.common.viewModels
import com.example.calmatemvvm.databinding.FragmentViewPagerBinding
import com.example.calmatemvvm.ui.common.BaseFragment
import com.example.calmatemvvm.ui.onboarding.screens.FirstScreen
import com.example.calmatemvvm.ui.onboarding.screens.SecondScreen
import com.example.calmatemvvm.ui.onboarding.screens.ThirdScreen

class ViewPagerFragment : BaseFragment<FragmentViewPagerBinding>() {

    override val viewModel by viewModels {
        ViewPagerViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentList = arrayListOf<Fragment>(
            FirstScreen(viewModel),
            SecondScreen(viewModel),
            ThirdScreen(viewModel)
        )
        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )
        binding.viewPager.adapter = adapter

    }

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentViewPagerBinding {

        return FragmentViewPagerBinding.inflate(inflater)
    }
}