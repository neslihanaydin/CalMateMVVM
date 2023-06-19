package com.example.calmatemvvm.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnLayout
import androidx.core.widget.doAfterTextChanged
import com.example.calmatemvvm.common.viewModels
import com.example.calmatemvvm.databinding.FragmentRegisterBinding
import com.example.calmatemvvm.ui.common.BaseFragment

class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {

    override val viewModel by viewModels {
        RegisterViewModel()
    }
    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentRegisterBinding {
        return FragmentRegisterBinding.inflate(inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeTextFields()
        binding.textSignIn.setOnClickListener {
            appViewModel.navigationUnit.navigate(
                RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
            )
        }
    }

    private fun observeTextFields() {

        // Validation for Username(Email)
        binding.username.doOnLayout {
            binding.username.apply {
                doAfterTextChanged {
                    viewModel.validate(it.toString(), RegisterValidator.RegisterInputTypes.USERNAME)
                }
            }
        }

        // Validation for Full Name
        binding.fullName.doOnLayout {
            binding.fullName.apply {
                doAfterTextChanged {
                    viewModel.validate(it.toString(), RegisterValidator.RegisterInputTypes.FULL_NAME)
                }
            }
        }

        // Validation for Password
        binding.password.doOnLayout {
            binding.password.apply {
                doAfterTextChanged {
                    viewModel.validate(it.toString(), RegisterValidator.RegisterInputTypes.PASSWORD)
                }
            }
        }

        // Validation for Password
        binding.confirmPassword.doOnLayout {
            binding.confirmPassword.apply {
                doAfterTextChanged {
                    viewModel.validate(it.toString(), RegisterValidator.RegisterInputTypes.PASSWORD_CONFIRMATION)
                }
            }
        }

    }


}