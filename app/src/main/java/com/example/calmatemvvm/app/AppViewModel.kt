package com.example.calmatemvvm.app

import androidx.lifecycle.ViewModel
import com.example.calmatemvvm.app.impl.NavigationUnitImpl
import com.example.calmatemvvm.app.impl.ResourcesUnitImpl
import com.example.calmatemvvm.app.unit.NavigationUnit
import com.example.calmatemvvm.app.unit.ResourcesUnit
import javax.inject.Inject

class AppViewModel @Inject constructor(
    private val internalResourcesUnit: ResourcesUnitImpl,
    private val internalNavigationUnit: NavigationUnitImpl
) : ViewModel() {
    val resourcesUnit: ResourcesUnit = internalResourcesUnit
    val navigationUnit: NavigationUnit = internalNavigationUnit
}