package com.example.calmatemvvm.ui.register

import android.util.Patterns

class RegisterValidator {

    enum class RegisterInputTypes {
        USERNAME,
        FULL_NAME,
        PASSWORD,
        PASSWORD_CONFIRMATION
    }

    fun validateField(input: String, type: RegisterInputTypes) : Boolean {
        return when(type) {
            RegisterInputTypes.USERNAME -> {
                (input.contains('@')) &&
                        Patterns.EMAIL_ADDRESS.matcher(input).matches()
            }
            RegisterInputTypes.FULL_NAME -> {
               "^[a-zA-Z]{4,}(?: [a-zA-Z]+){0,2}$".toRegex().matches(input)
               /*
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

                */
            }
            RegisterInputTypes.PASSWORD -> {
                "^(?=.*\\d)[a-zA-Z\\d]{4,}$".toRegex().matches(input)
            }
            RegisterInputTypes.PASSWORD_CONFIRMATION -> {
                "^(?=.*\\d)[a-zA-Z\\d]{4,}$".toRegex().matches(input)
            }
        }
    }
}