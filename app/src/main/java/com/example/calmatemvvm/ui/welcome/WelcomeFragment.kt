package com.example.calmatemvvm.ui.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.calmatemvvm.R
import com.example.calmatemvvm.common.viewModels
import com.example.calmatemvvm.databinding.FragmentWelcomeBinding
import com.example.calmatemvvm.ui.common.BaseFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class WelcomeFragment : BaseFragment<FragmentWelcomeBinding>() {

    override val viewModel by viewModels {
        WelcomeViewModel()
    }
    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentWelcomeBinding {

        // Bottom Navigation Bar Invisible
        val bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView?.visibility = View.GONE
        return FragmentWelcomeBinding.inflate(inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbarVisibility(false)
        binding.getStartedButton.setOnClickListener {
            appViewModel.navigationUnit.navigate(
                WelcomeFragmentDirections.actionWelcomeFragmentToRegisterFragment()
            )
        }
        binding.textSignIn.setOnClickListener {
            appViewModel.navigationUnit.navigate(
                WelcomeFragmentDirections.actionWelcomeFragmentToLoginFragment()
            )
        }
    }

}