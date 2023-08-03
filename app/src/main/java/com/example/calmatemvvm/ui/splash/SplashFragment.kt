package com.example.calmatemvvm.ui.splash

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.calmatemvvm.R
import com.example.calmatemvvm.common.viewModels
import com.example.calmatemvvm.common.viewScope
import com.example.calmatemvvm.data.DatabaseHelper
import com.example.calmatemvvm.databinding.FragmentSplashBinding
import com.example.calmatemvvm.ui.common.BaseFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashFragment : BaseFragment<FragmentSplashBinding>() {

    private var databaseHelper : DatabaseHelper? = null

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

        return FragmentSplashBinding.inflate(inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setToolbarVisibility(false)

        viewScope.launch {
            delay(2000)

                // check shared preferences
                val sharedPreferences = requireContext().getSharedPreferences("rememberMe", 0)
                val username = sharedPreferences.getString("username", "")
                if (username != null && username != "") {
                    databaseHelper = DatabaseHelper(requireContext())
                    val user = databaseHelper!!.getUserByEmail(username)
                    appViewModel.setLoggedUser(user)
                    appViewModel.navigationUnit.navigate(
                        R.id.homeFragment,
                        null
                    )
                } else {
                    appViewModel.navigationUnit.navigate(
                        R.id.welcomeFragment,
                        null
                    )
                }
            }
        }
    }


