package com.example.calmatemvvm.ui.register

import androidx.lifecycle.MutableLiveData

data class FieldErrorStatus(
    val isActive : MutableLiveData<Boolean> = MutableLiveData(false),
    val errorText : MutableLiveData<String> = MutableLiveData("")
)
