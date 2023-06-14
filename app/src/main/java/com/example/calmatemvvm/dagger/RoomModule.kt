package com.example.calmatemvvm.dagger

import android.content.Context
import androidx.room.Room
import com.example.calmatemvvm.data.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule(context: Context) {

    private var appDatabase: AppDatabase =
        Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "app_database").build()

    @Singleton
    @Provides
    fun provideAppDatabase(): AppDatabase {
        return appDatabase
    }
}
