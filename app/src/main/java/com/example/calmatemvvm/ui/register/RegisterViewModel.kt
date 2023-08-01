package com.example.calmatemvvm.ui.register

import com.example.calmatemvvm.app.AppViewModel
import com.example.calmatemvvm.ui.common.BaseViewModel

class RegisterViewModel(private val appViewModel: AppViewModel) : BaseViewModel() {

    private val validator: RegisterValidator = RegisterValidator()

    val formValidationStatus : FormFieldsValidationStatus = FormFieldsValidationStatus()

    fun validate(input: String, type: RegisterValidator.RegisterInputTypes){
        val result = validator.validateField(input, type)
        when(type) {
            RegisterValidator.RegisterInputTypes.USERNAME -> {
                formValidationStatus.userNameHasError.isValid.value = result
                formValidationStatus.userNameHasError.errorText.value = type.errorText
            }
            RegisterValidator.RegisterInputTypes.FULL_NAME -> {
                formValidationStatus.fullNameHasError.isValid.value = result
                formValidationStatus.fullNameHasError.errorText.value = type.errorText
            }
            RegisterValidator.RegisterInputTypes.PASSWORD -> {
                formValidationStatus.passwordHasError.isValid.value = result
                formValidationStatus.passwordHasError.errorText.value = type.errorText
            }
            RegisterValidator.RegisterInputTypes.PASSWORD_CONFIRMATION -> {
                formValidationStatus.passwordConfirmationHasError.isValid.value = result
                formValidationStatus.passwordConfirmationHasError.errorText.value = type.errorText
            }
        }
    }

    fun register(username: String, password: String, timestampString: String, firstName: String, lastName: String): String {
        val result = appViewModel.dbHelper.addUser(username, username,timestampString,password,firstName, lastName)
        return result
    }
}