package com.example.calmatemvvm.common

import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope

val Fragment.viewScope: CoroutineScope get() = viewLifecycleOwner.lifecycleScope

