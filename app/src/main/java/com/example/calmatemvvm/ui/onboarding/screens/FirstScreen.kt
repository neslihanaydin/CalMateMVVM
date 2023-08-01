package com.example.calmatemvvm.ui.onboarding.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.calmatemvvm.R
import com.example.calmatemvvm.databinding.FragmentFirstScreenBinding
import com.example.calmatemvvm.ui.common.BaseFragment
import com.example.calmatemvvm.ui.common.BaseViewModel


class FirstScreen(override val viewModel: BaseViewModel) : BaseFragment<FragmentFirstScreenBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)

        binding.next.setOnClickListener {
            viewPager?.currentItem = 1
        }
    }

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentFirstScreenBinding {
        return FragmentFirstScreenBinding.inflate(inflater)
    }

}