package com.example.calmatemvvm.dagger

import com.example.calmatemvvm.app.AppViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [CoreModule::class, RoomModule::class])
interface AppComponent {
    fun getAppViewModel(): AppViewModel
}