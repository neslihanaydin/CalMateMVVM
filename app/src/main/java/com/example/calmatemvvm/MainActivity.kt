package com.example.calmatemvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.calmatemvvm.app.AppViewModel
import com.example.calmatemvvm.dagger.CoreModule
import com.example.calmatemvvm.dagger.DaggerAppComponent
import com.example.calmatemvvm.dagger.RoomModule

class MainActivity : AppCompatActivity() {

    private lateinit var appViewModel: AppViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val appComponent = DaggerAppComponent.builder()
            .build()
        val appViewModel = appComponent.getAppViewModel().also {
            this.appViewModel = it
        }
    }
}