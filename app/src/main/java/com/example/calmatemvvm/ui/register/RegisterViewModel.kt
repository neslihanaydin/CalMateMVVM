package com.example.calmatemvvm.ui.register

import com.example.calmatemvvm.ui.common.BaseViewModel

class RegisterViewModel : BaseViewModel() {

    private val validator: RegisterValidator = RegisterValidator()

    val formValidationStatus : FormFieldsValidationStatus = FormFieldsValidationStatus()

    fun validate(input: String, type: RegisterValidator.RegisterInputTypes){
        val result = validator.validateField(input, type)
        when(type) {
            RegisterValidator.RegisterInputTypes.USERNAME -> {
                formValidationStatus.userNameHasError.isActive.value = result
                formValidationStatus.userNameHasError.errorText.value = type.errorText
            }
            RegisterValidator.RegisterInputTypes.FULL_NAME -> {
                formValidationStatus.fullNameHasError.isActive.value = result
                formValidationStatus.fullNameHasError.errorText.value = type.errorText
            }
            RegisterValidator.RegisterInputTypes.PASSWORD -> {
                formValidationStatus.passwordHasError.isActive.value = result
                formValidationStatus.passwordHasError.errorText.value = type.errorText
            }
            RegisterValidator.RegisterInputTypes.PASSWORD_CONFIRMATION -> {
                formValidationStatus.passwordConfirmationHasError.isActive.value = result
                formValidationStatus.passwordConfirmationHasError.errorText.value = type.errorText
            }
        }
    }
}