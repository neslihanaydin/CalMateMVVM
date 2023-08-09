package com.example.calmatemvvm.ui.fitness

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.calmatemvvm.app.AppViewModel
import com.example.calmatemvvm.model.DailyFitnessModel
import com.example.calmatemvvm.repository.FitnessRepository
import com.example.calmatemvvm.repository.FitnessRepositoryImpl
import com.example.calmatemvvm.repository.SharedPreferencesRepository
import com.example.calmatemvvm.repository.SharedPreferencesRepositoryImpl
import com.example.calmatemvvm.ui.common.BaseViewModel

class FitnessViewModel(private val appViewModel: AppViewModel) : BaseViewModel() {

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

    fun checkGoalReached(currentSteps: Int): Boolean {
        val user = appViewModel.getUser()
        val userId = appViewModel.dbHelper.getUserIdByEmail(user!!.email)
        var response = appViewModel.dbHelper.getUserMoveGoal(userId)
        if (currentSteps >= response) {
            return true
        }
        return false
    }
}