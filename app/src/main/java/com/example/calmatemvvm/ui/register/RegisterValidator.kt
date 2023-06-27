package com.example.calmatemvvm.ui.register

import android.util.Patterns

class RegisterValidator {
    enum class RegisterInputTypes(val errorText : String) {
        USERNAME(INVALID_USERNAME),
        FULL_NAME(INVALID_FULL_NAME),
        PASSWORD(INVALID_PASSWORD),
        PASSWORD_CONFIRMATION(NOT_MATCHED_PASSWORD),
    }

    fun validateField(input: String, type: RegisterInputTypes) : Boolean {
        return when(type) {
            RegisterInputTypes.USERNAME -> {
                (input.contains('@')) &&
                        Patterns.EMAIL_ADDRESS.matcher(input).matches()
            }
            RegisterInputTypes.FULL_NAME -> {

               val splittedInput = input.split(' ')
                if (splittedInput.size <= 1){
                    false
                } else {
                    splittedInput.forEach{
                        if(it.length < 2) {
                            return false
                        }
                    }
                    true
                }
            }
            RegisterInputTypes.PASSWORD -> {
                "^(?=.*\\d)[a-zA-Z\\d]{4,}$".toRegex().matches(input)
            }
            RegisterInputTypes.PASSWORD_CONFIRMATION -> {
                "^(?=.*\\d)[a-zA-Z\\d]{4,}$".toRegex().matches(input)
            }
        }
    }

    companion object {
        private const val INVALID_USERNAME = "Invalid Email"
        private const val INVALID_FULL_NAME = "Invalid Name"
        private const val INVALID_PASSWORD = "Invalid Password"
        private const val NOT_MATCHED_PASSWORD = "Passwords do not match"
    }
}