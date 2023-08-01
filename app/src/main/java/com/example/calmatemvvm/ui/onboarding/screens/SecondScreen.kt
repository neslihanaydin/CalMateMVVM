package com.example.calmatemvvm.ui.onboarding.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.calmatemvvm.R
import com.example.calmatemvvm.databinding.FragmentSecondScreenBinding
import com.example.calmatemvvm.ui.common.BaseFragment
import com.example.calmatemvvm.ui.common.BaseViewModel

class SecondScreen(override val viewModel: BaseViewModel) : BaseFragment<FragmentSecondScreenBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)

        binding.next2.setOnClickListener {
            viewPager?.currentItem = 2
        }
    }

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentSecondScreenBinding {
        return FragmentSecondScreenBinding.inflate(inflater)
    }

}