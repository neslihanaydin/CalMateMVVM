package com.example.calmatemvvm.common

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("errorText", "isValid")
fun setError(tInputLayout: TextInputLayout, str: String, isValid: Boolean) {
    if (!isValid) {
        tInputLayout.setError(str)
    } else {
        tInputLayout.setError(null)
    }
}