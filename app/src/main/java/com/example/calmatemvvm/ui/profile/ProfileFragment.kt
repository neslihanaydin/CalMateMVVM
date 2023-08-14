package com.example.calmatemvvm.ui.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.example.calmatemvvm.common.viewModels
import android.view.ViewGroup
import android.widget.Toast
import com.example.calmatemvvm.R
import com.example.calmatemvvm.databinding.FragmentProfileBinding
import com.example.calmatemvvm.ui.common.BaseFragment

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    override val viewModel by viewModels{
        ProfileViewModel(appViewModel)
    }

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentProfileBinding {
        return FragmentProfileBinding.inflate(inflater)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvUserInfo.setText(appViewModel.getUser()?.first_name ?: "")
        binding.btnSaveProfile.setOnClickListener(View.OnClickListener {
            val prePassword = binding.editCurrentPassword.text.toString()
            val newPassword = binding.editNewPassword.text.toString()
            val response = viewModel.updateUserProfile(prePassword, newPassword)
            if (response >= 1) {
                binding.editNewPassword.setText("")
                binding.editCurrentPassword.setText("")
                Toast.makeText(context, "Password updated", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Password not updated", Toast.LENGTH_SHORT).show()
            }
        })
        binding.btnLogout.setOnClickListener(View.OnClickListener {
            appViewModel.logOut()
            // remove the shared preferences
            val sharedPref = activity?.getSharedPreferences("rememberMe", 0)
            val editor = sharedPref?.edit()
            editor?.clear()
            editor?.apply()

            // navigate to the welcome page
            appViewModel.navigationUnit.navigate(
                R.id.welcomeFragment,
                null
            )        })
    }


}