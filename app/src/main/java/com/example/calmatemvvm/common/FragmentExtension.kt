package com.example.calmatemvvm.common

import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.fragment.app.viewModels
import kotlinx.coroutines.CoroutineScope

val Fragment.viewScope: CoroutineScope get() = viewLifecycleOwner.lifecycleScope

inline fun <reified VM: ViewModel> Fragment.viewModels(
    noinline ownerProducer: () -> ViewModelStoreOwner = { this },
    crossinline provider: () -> VM
) = viewModels<VM>(ownerProducer) { createViewModelFactory(provider) }

inline fun <reified VM: ViewModel> Fragment.parentViewModels() = lazy (LazyThreadSafetyMode.NONE) {
    getParentViewModel<VM>()
}

inline fun <reified VM : ViewModel> Fragment.getParentViewModel() = ViewModelProvider(requireParentFragment()).get<VM>()
