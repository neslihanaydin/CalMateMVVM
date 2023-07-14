package com.example.calmatemvvm.ui.fitness

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.calmatemvvm.model.DailyFitnessModel
import com.example.calmatemvvm.repository.FitnessRepository
import com.example.calmatemvvm.repository.FitnessRepositoryImpl
import com.example.calmatemvvm.repository.SharedPreferencesRepository
import com.example.calmatemvvm.repository.SharedPreferencesRepositoryImpl
import com.example.calmatemvvm.ui.common.BaseViewModel

class FitnessViewModel : BaseViewModel() {

    val fitnessRepo: FitnessRepository = FitnessRepositoryImpl()
    val sharedPreferencesRepo: SharedPreferencesRepository = SharedPreferencesRepositoryImpl()

    fun getDailyFitnessData(context: Context): LiveData<DailyFitnessModel> {
        var dailyFitnessLiveData = fitnessRepo.getDailyFitnessData(context)
        return dailyFitnessLiveData
    }

    fun saveObjectiveSteps(context: Context, objectiveSteps: Int) {
        sharedPreferencesRepo.saveObjectiveSteps(context, objectiveSteps)
    }
    fun loadObjectiveSteps(context: Context): Int {
        return sharedPreferencesRepo.loadObjectiveSteps(context)
    }
}