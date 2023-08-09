package com.example.calmatemvvm.ui.setgoal

import com.example.calmatemvvm.app.AppViewModel
import com.example.calmatemvvm.ui.common.BaseViewModel

class SetGoalViewModel(private val appViewModel: AppViewModel) : BaseViewModel() {

    fun setGoal(goal: Int) : String {
        val user = appViewModel.getUser()
        val userId = appViewModel.dbHelper.getUserIdByEmail(user!!.email)
        val response = appViewModel.dbHelper.setUserMoveGoal(userId, goal)
        return response
    }
}