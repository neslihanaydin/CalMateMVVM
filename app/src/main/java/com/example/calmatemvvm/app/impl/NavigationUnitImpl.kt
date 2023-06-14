package com.example.calmatemvvm.app.impl

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import com.example.calmatemvvm.app.unit.NavigationUnit
import javax.inject.Inject

class NavigationUnitImpl @Inject constructor() : NavigationUnit {

    private var navController: NavController? = null

    override fun setNavController(navController: NavController) {
        this.navController = navController
    }

    override fun navigateBack() {
        navController?.popBackStack()
    }

    override fun navigate(
        pageResId: Int,
        navArgs: Bundle?
    ) {
        val navOptionsBuilder = getDefaultNavOptions()
        navController?.navigate(pageResId, navArgs, navOptionsBuilder.build())
    }

    override fun navigate(
        directions: NavDirections
    ) {
        val navOptionsBuilder = getDefaultNavOptions()
        navController?.navigate(directions, navOptionsBuilder.build())
    }

    override fun popAndNavigate(
        pageResId: Int,
        navArgs: Bundle?
    ) {
        val navOptionsBuilder = getDefaultNavOptions()
        navController?.currentDestination?.let {
            navOptionsBuilder.setPopUpTo(it.id, true)
        }
        navController?.navigate(pageResId, navArgs, navOptionsBuilder.build())
    }

    override fun popAndNavigate(
        directions: NavDirections
    ) {
        val navOptionsBuilder = getDefaultNavOptions()
        navController?.currentDestination?.let {
            navOptionsBuilder.setPopUpTo(it.id, true)
        }
        navController?.navigate(directions, navOptionsBuilder.build())
    }

    private fun getDefaultNavOptions(): NavOptions.Builder {
        return NavOptions.Builder()
    }
}