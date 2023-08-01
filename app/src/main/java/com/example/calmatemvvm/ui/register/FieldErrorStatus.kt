package com.example.calmatemvvm.ui.register

import androidx.lifecycle.MutableLiveData

data class FieldErrorStatus(
    val isValid : MutableLiveData<Boolean> = MutableLiveData(false),
    val errorText : MutableLiveData<String> = MutableLiveData("")
)
