package com.example.calmatemvvm.ui.register

import androidx.lifecycle.MutableLiveData

data class FormFieldsValidationStatus(
    val userNameHasError : FieldErrorStatus = FieldErrorStatus(),
    val fullNameHasError : FieldErrorStatus = FieldErrorStatus(),
    val passwordHasError : FieldErrorStatus = FieldErrorStatus(),
    val passwordConfirmationHasError : FieldErrorStatus = FieldErrorStatus(),
) {
  fun validatePage(isTermChecked : Boolean) : Boolean {
      val result = (userNameHasError.isActive.value == false &&
              fullNameHasError.isActive.value == false &&
              passwordHasError.isActive.value == false &&
              passwordConfirmationHasError.isActive.value == false &&
              isTermChecked)
      return result
  }
}