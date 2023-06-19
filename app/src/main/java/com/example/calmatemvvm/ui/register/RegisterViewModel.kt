package com.example.calmatemvvm.ui.register

import com.example.calmatemvvm.ui.common.BaseViewModel

class RegisterViewModel : BaseViewModel() {

    private val validator: RegisterValidator = RegisterValidator()

    fun validate(input: String, type: RegisterValidator.RegisterInputTypes){
        val response = validator.validateField(input, type)
        println("Response : $response")
    }
}