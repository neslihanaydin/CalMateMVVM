package com.example.calmatemvvm.ui.login

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.calmatemvvm.R
import com.example.calmatemvvm.common.viewModels
import com.example.calmatemvvm.databinding.FragmentLoginBinding
import com.example.calmatemvvm.ui.common.BaseFragment

class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    override val viewModel by viewModels {
        LoginViewModel(appViewModel)
    }
    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentLoginBinding {
        return FragmentLoginBinding.inflate(inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButton.setOnClickListener {
            val email = binding.username.text.toString()
            val password = binding.password.text.toString()
            // send email and password to the view model
            val status = viewModel.login(email, password)
            if(status) {
                // Save user to the shared preferences
                val rememberUser: Boolean =  binding.rememberMe.isChecked
                if (rememberUser) {
                    // Save username to the shared preferences
                    val sharedPreferences: SharedPreferences = requireContext().getSharedPreferences("rememberMe", Context.MODE_PRIVATE)
                    val editor: SharedPreferences.Editor = sharedPreferences.edit()
                    editor.putString("username", binding.username.text.toString())
                    editor.apply()
                }
                appViewModel.navigationUnit.navigate(
                    R.id.homeFragment,
                    null
                )
            } else {
                binding.username.error = "Invalid email or password"
                binding.password.error = "Invalid email or password"
            }

        }


    }
}