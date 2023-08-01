package com.example.calmatemvvm.ui.register

data class FormFieldsValidationStatus(
    val userNameHasError : FieldErrorStatus = FieldErrorStatus(),
    val fullNameHasError : FieldErrorStatus = FieldErrorStatus(),
    val passwordHasError : FieldErrorStatus = FieldErrorStatus(),
    val passwordConfirmationHasError : FieldErrorStatus = FieldErrorStatus(),
) {
  fun validatePage(isTermChecked : Boolean) : Boolean {
      val result = (userNameHasError.isValid.value == true &&
              fullNameHasError.isValid.value == true &&
              passwordHasError.isValid.value == true &&
              passwordConfirmationHasError.isValid.value == true &&
              isTermChecked)
      return result
  }
}