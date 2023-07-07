package com.example.calmatemvvm.ui.register

import android.os.Bundle
import android.os.Handler
import android.os.Looper
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

    // timer
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentRegisterBinding {
        return FragmentRegisterBinding.inflate(inflater).also {
            it.viewModel = viewModel
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeTextFields()
        binding.textSignIn.setOnClickListener {
            appViewModel.navigationUnit.navigate(
                RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
            )
        }
        binding.registerButton.setOnClickListener {
            val isTermChecked = binding.acceptCheck.isChecked
            if(viewModel.formValidationStatus.validatePage(isTermChecked)) {
                // TODO: Register user, Navigate next page
            }
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
                    handler.postDelayed({
                        viewModel.validate(it.toString(), RegisterValidator.RegisterInputTypes.FULL_NAME)
                    }, INPUT_DELAY)
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

    companion object{
        private const val INPUT_DELAY = 500L
    }
}