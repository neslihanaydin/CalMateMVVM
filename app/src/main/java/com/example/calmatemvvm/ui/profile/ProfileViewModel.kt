package com.example.calmatemvvm.ui.profile

import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.calmatemvvm.app.AppViewModel
import com.example.calmatemvvm.ui.common.BaseViewModel

class ProfileViewModel(private val appViewModel: AppViewModel) : BaseViewModel() {

    fun updateUserProfile(prePassword: String, newPassword: String): Int{
        val user = appViewModel.getUser()
        val userObj = appViewModel.dbHelper.getUserByEmail(user!!.email)


        if (userObj != null) {
            if (prePassword == userObj.password) {
                val response = appViewModel.dbHelper.updateUserPassword(user.email, newPassword)
                user.password = newPassword
                appViewModel.setLoggedUser(user)
                return response
            }
        }
        return -1
    }

}

