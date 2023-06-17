package com.example.calmatemvvm.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore

inline fun <VM> createViewModelFactory(crossinline provider: () -> VM) =
    object : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <VM : ViewModel> create(modelClass: Class<VM>) = provider() as VM
    }

fun <T : ViewModel> injectViewModel(viewModel: T, viewModelStore: ViewModelStore): T {
    return ViewModelProvider(viewModelStore, createViewModelFactory { viewModel })[viewModel.javaClass]
}