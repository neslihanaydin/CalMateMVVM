package com.example.calmatemvvm.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnLayout
import androidx.core.widget.doAfterTextChanged
import com.example.calmatemvvm.R
import com.example.calmatemvvm.common.viewModels
import com.example.calmatemvvm.databinding.FragmentRegisterBinding
import com.example.calmatemvvm.model.User
import com.example.calmatemvvm.ui.common.BaseFragment
import com.google.android.material.snackbar.Snackbar


class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {


    override val viewModel by viewModels {
        RegisterViewModel(appViewModel)
    }

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

                val username = binding.username.text.toString()
                val password = binding.password.text.toString()
                val fullName = binding.fullName.text.toString()
                val firstName = fullName.split(" ")[0]
                val lastName = fullName.split(" ")[1]

                val currentTimeMillis = System.currentTimeMillis()
                val timestampString = currentTimeMillis.toString()

                val result = viewModel.register(username, password, timestampString, firstName, lastName)
                showSnackbar(result)

                val user =
                    User(
                    username = username,
                    email = username,
                    password = password,
                    first_name = firstName,
                    last_name = lastName,
                    move_goal = 0,
                    created_at = timestampString
                )
                appViewModel.setLoggedUser(user)
                appViewModel.navigationUnit.navigate(
                    R.id.viewPagerFragment,
                    null
                )

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

    private fun showSnackbar(message: String) {
        Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG).show()
    }
}