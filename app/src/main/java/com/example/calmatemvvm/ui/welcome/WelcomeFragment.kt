package com.example.calmatemvvm.ui.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.calmatemvvm.databinding.FragmentWelcomeBinding
import com.example.calmatemvvm.ui.common.BaseFragment

class WelcomeFragment : BaseFragment<FragmentWelcomeBinding>() {
    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentWelcomeBinding {
        return FragmentWelcomeBinding.inflate(inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.getStartedButton.setOnClickListener {
            // TODO: Navigate associated page
        }
        binding.textSignIn.setOnClickListener {
            // TODO: Navigate associated page
        }
    }

}