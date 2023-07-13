package com.example.calmatemvvm.ui.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewbinding.ViewBinding
import com.example.calmatemvvm.app.AppViewModel
import com.google.android.material.appbar.MaterialToolbar

abstract class BaseFragment<T : ViewBinding>: Fragment() {

    protected val appViewModel by activityViewModels<AppViewModel>()

    protected lateinit var binding: T

    protected abstract val viewModel: BaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return onCreateBinding(inflater, container, savedInstanceState).also {
            binding = it
            if (it is ViewDataBinding) {
                it.lifecycleOwner = viewLifecycleOwner
            }
        }.root
    }

    protected abstract fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): T

    protected fun setToolbarVisibility(isVisible: Boolean) {
        val activity = requireActivity()
        val toolbar = activity.findViewById<MaterialToolbar>(com.example.calmatemvvm.R.id.topAppBar)
        toolbar.visibility = if (isVisible) View.VISIBLE else View.GONE
    }
}