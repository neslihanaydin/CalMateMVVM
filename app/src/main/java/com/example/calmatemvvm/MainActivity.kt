package com.example.calmatemvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.calmatemvvm.app.AppViewModel
import com.example.calmatemvvm.common.injectViewModel
import com.example.calmatemvvm.dagger.CoreModule
import com.example.calmatemvvm.dagger.DaggerAppComponent
import com.example.calmatemvvm.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appViewModel: AppViewModel
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val appComponent = DaggerAppComponent.builder()
            .coreModule(CoreModule(this))
            .build()
        val appViewModel = appComponent.getAppViewModel().also {
            this.appViewModel = it
        }

        injectViewModel(appViewModel, viewModelStore)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_nav_host_container) as NavHostFragment
        navController = navHostFragment.navController

        appViewModel.navigationUnit.setNavController(navController)
        appViewModel.isFinish.observe(this) {
            if(it){
                finishAffinity()
            }
        }

        val topAppBar = binding.topAppBar

        topAppBar.setNavigationOnClickListener {
            appViewModel.navigationUnit.navigateBack()
        }

        topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {

                else -> false
            }
        }

        val navigationView = binding.bottomNavigation

        navigationView.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.page_home -> {
                    appViewModel.navigationUnit.navigate(
                        R.id.homeFragment,
                        null
                    )
                    true
                }
                R.id.page_favorite -> {
                    true
                }
                R.id.page_meditation -> {
                    appViewModel.navigationUnit.navigate(
                        R.id.meditationListFragment,
                        null
                    )
                    true
                }
                R.id.page_profile -> {
                    true
                }
                R.id.page_notifications -> {
                    true
                }
                else -> false
            }
        }

        // Prevent start fragments again
        navigationView.setOnItemReselectedListener { item ->
            when(item.itemId) {
                R.id.page_home -> {
                    appViewModel.navigationUnit.navigate(
                        R.id.homeFragment,
                        null
                    )
                    true
                }
                R.id.page_favorite -> {
                    true
                }
                R.id.page_meditation -> {
                    true
                }
                R.id.page_profile -> {
                    true
                }
                R.id.page_notifications -> {
                    true
                }
            }
        }


    }
}