package com.example.calmatemvvm.dagger

import android.content.Context
import android.content.res.Resources
import com.example.calmatemvvm.data.DatabaseHelper
import com.example.calmatemvvm.model.User
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CoreModule(private val context: Context) {

    @Provides
    fun provideContext(): Context = context

    @Singleton
    @Provides
    fun provideResources(context: Context): Resources{
        return context.resources
    }

    @Singleton
    @Provides
    fun provideUser(): User {
        return User()
    }

    @Singleton
    @Provides
    fun provideDatabaseHelper(context: Context): DatabaseHelper {
        return DatabaseHelper(context)
    }
}