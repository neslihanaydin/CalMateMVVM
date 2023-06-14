package com.example.calmatemvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.calmatemvvm.app.AppViewModel
import com.example.calmatemvvm.dagger.CoreModule
import com.example.calmatemvvm.dagger.DaggerAppComponent
import com.example.calmatemvvm.dagger.RoomModule
import com.example.calmatemvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appViewModel: AppViewModel
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val appComponent = DaggerAppComponent.builder()
            .build()
        val appViewModel = appComponent.getAppViewModel().also {
            this.appViewModel = it
        }

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_nav_host_container) as NavHostFragment
        navController = navHostFragment.navController

        appViewModel.navigationUnit.setNavController(navController)

    }
}