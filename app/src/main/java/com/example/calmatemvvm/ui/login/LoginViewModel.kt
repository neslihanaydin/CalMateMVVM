package com.example.calmatemvvm.ui.login

import com.example.calmatemvvm.app.AppViewModel
import com.example.calmatemvvm.ui.common.BaseViewModel

class LoginViewModel(private val appViewModel: AppViewModel) : BaseViewModel() {

    fun login(email: String, password: String): Boolean {

        val status = appViewModel.dbHelper.checkUser(email, password)
        if (status) {
            val user = appViewModel.dbHelper.getUserByEmail(email)
            // Set logged user to the app view model
            appViewModel.setLoggedUser(user)
            return true
        }
        return false
    }

}