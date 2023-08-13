package com.example.calmatemvvm.app

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calmatemvvm.app.impl.NavigationUnitImpl
import com.example.calmatemvvm.app.impl.ResourcesUnitImpl
import com.example.calmatemvvm.app.unit.NavigationUnit
import com.example.calmatemvvm.app.unit.ResourcesUnit
import com.example.calmatemvvm.data.DatabaseHelper
import com.example.calmatemvvm.model.User
import javax.inject.Inject

class AppViewModel @Inject constructor(
    private val internalResourcesUnit: ResourcesUnitImpl,
    private val internalNavigationUnit: NavigationUnitImpl,
    val dbHelper: DatabaseHelper
) : ViewModel() {

    val resourcesUnit: ResourcesUnit = internalResourcesUnit
    val navigationUnit: NavigationUnit = internalNavigationUnit
    // When application need to exit, set true
    private val _isFinish = MutableLiveData(false)
    val isFinish: LiveData<Boolean> = _isFinish
    private var user: User? = null

    fun appExit(){
        _isFinish.value = true
    }

    // setLoggedUser
    fun setLoggedUser(user: User){
        this.user = user
    }

    // getLoggedUser
    fun getUser(): User? {
        return this.user
    }

    fun logOut() {
        this.user = null
    }

    fun setMood(mood: String) {
        if (this.user != null) {
            this.user!!.mood = this.user!!.mood ?: mood // if mood is null, set mood
        }
    }


    fun getMood(): String? {
        return this.user?.mood
    }



}