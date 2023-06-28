package com.example.calmatemvvm.ui.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    protected val _isLoading = MutableLiveData(true)
    val isLoading: LiveData<Boolean> = _isLoading
}