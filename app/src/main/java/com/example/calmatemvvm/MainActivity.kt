package com.example.calmatemvvm

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.calmatemvvm.app.AppViewModel
import com.example.calmatemvvm.common.injectViewModel
import com.example.calmatemvvm.dagger.CoreModule
import com.example.calmatemvvm.dagger.DaggerAppComponent
import com.example.calmatemvvm.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.fitness.FitnessOptions
import com.google.android.gms.fitness.data.DataType
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appViewModel: AppViewModel
    private lateinit var navController: NavController

    private val GOOGLE_FIT_PERMISSIONS_REQUEST_CODE = 1
    private val runningQOrLater =
        android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q

    private val fitnessOptions = FitnessOptions.builder()
        .addDataType(DataType.TYPE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
        .addDataType(DataType.AGGREGATE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
        .addDataType(DataType.TYPE_CALORIES_EXPENDED, FitnessOptions.ACCESS_READ)
        .addDataType(DataType.AGGREGATE_CALORIES_EXPENDED, FitnessOptions.ACCESS_READ)
        .addDataType(DataType.TYPE_DISTANCE_DELTA, FitnessOptions.ACCESS_READ)
        .addDataType(DataType.AGGREGATE_DISTANCE_DELTA, FitnessOptions.ACCESS_READ)
        .build()
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

        checkPermissionsAndRun(GOOGLE_FIT_PERMISSIONS_REQUEST_CODE)

    }

    private fun checkPermissionsAndRun(googleFitPermissionsRequestCode: Int) {
        if (permissionApproved()) {
            fitSignIn(googleFitPermissionsRequestCode)
        } else {
            requestRuntimePermissions(googleFitPermissionsRequestCode)
        }
    }

    private fun requestRuntimePermissions(requestCode: Int) {
        val shouldProvideRationale =
            ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.ACCESS_FINE_LOCATION)

        // Provide an additional rationale to the user. This would happen if the user denied the
        // request previously, but didn't check the "Don't ask again" checkbox.
        requestCode.let {
            if (shouldProvideRationale) {
                Log.i(ContentValues.TAG, "Displaying permission rationale to provide additional context.")
                Snackbar.make(
                    findViewById(android.R.id.content),
                    "Permission Denied",
                    Snackbar.LENGTH_INDEFINITE)
                    .setAction("Settings") {
                        // Request permission
                        ActivityCompat.requestPermissions(this,
                            arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                            requestCode)
                    }
                    .show()
            } else {
                Log.i(ContentValues.TAG, "Requesting permission")
                // Request permission. It's possible this can be auto answered if device policy
                // sets the permission in a given state or the user denied the permission
                // previously and checked "Never ask again".
                ActivityCompat.requestPermissions(this,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                    requestCode)
            }
        }
        checkPermissionsAndRun(requestCode)
    }

    private fun permissionApproved(): Boolean {
        val approved = if (runningQOrLater) {
            PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
        } else {
            true
        }
        return approved
    }

    private fun fitSignIn(requestCode: Int) {
        if (!GoogleSignIn.hasPermissions(getGoogleAccount(), fitnessOptions)) {
            requestPermissions()
        } else {
            loadNav_controller()
        }
    }

    private fun getGoogleAccount() = GoogleSignIn.getAccountForExtension(this, fitnessOptions)

    private fun requestPermissions() {
        GoogleSignIn.requestPermissions(this,
            GOOGLE_FIT_PERMISSIONS_REQUEST_CODE,
            getGoogleAccount(),
            fitnessOptions)
    }

    private fun loadNav_controller() {

        val currentDestinationId = navController.currentDestination?.id

        if (currentDestinationId != null) {
            navController.navigate(currentDestinationId)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            Activity.RESULT_OK -> when (requestCode) {
                GOOGLE_FIT_PERMISSIONS_REQUEST_CODE -> loadNav_controller()
                else -> {
                    // No Google Fit result
                }
            }
            else -> {
                Toast.makeText(this, "Permission not granted", Toast.LENGTH_LONG).show()
                requestPermissions()
            }
        }
    }
}