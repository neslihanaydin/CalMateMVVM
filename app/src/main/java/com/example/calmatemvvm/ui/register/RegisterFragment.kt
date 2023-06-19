package com.example.calmatemvvm.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.calmatemvvm.databinding.FragmentRegisterBinding
import com.example.calmatemvvm.ui.common.BaseFragment

class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {
    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentRegisterBinding {
        return FragmentRegisterBinding.inflate(inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textSignIn.setOnClickListener {
            appViewModel.navigationUnit.navigate(
                RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
            )
        }
    }

}