package com.example.calmatemvvm.app.unit

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDirections

interface NavigationUnit {
    fun setNavController(navController: NavController)

    fun navigateBack()

    fun navigate(pageResId: Int, navArgs: Bundle?)

    fun navigate(directions: NavDirections)

    fun popAndNavigate(directions: NavDirections)

    fun popAndNavigate(pageResId: Int, navArgs: Bundle?)

}