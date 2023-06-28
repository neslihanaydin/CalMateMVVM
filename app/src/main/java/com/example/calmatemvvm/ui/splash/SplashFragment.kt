package com.example.calmatemvvm.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.calmatemvvm.R
import com.example.calmatemvvm.common.viewModels
import com.example.calmatemvvm.common.viewScope
import com.example.calmatemvvm.databinding.FragmentSplashBinding
import com.example.calmatemvvm.ui.common.BaseFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashFragment : BaseFragment<FragmentSplashBinding>() {

    override val viewModel by viewModels {
        SplashViewModel()
    }
    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentSplashBinding {
        // Bottom Navigation Bar Invisible
        val bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView?.visibility = View.GONE
        // bottomNavigationView?.visibility = View.VISIBLE
        return FragmentSplashBinding.inflate(inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewScope.launch {
            delay(2000)
            appViewModel.navigationUnit.navigate(
                SplashFragmentDirections.actionSplashFragmentToWelcomeFragment()
            )
        }
    }
}