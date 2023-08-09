package com.example.calmatemvvm.ui.setgoal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.calmatemvvm.R
import com.example.calmatemvvm.common.viewModels
import com.example.calmatemvvm.databinding.FragmentSetGoalBinding
import com.example.calmatemvvm.ui.common.BaseFragment

class SetGoalFragment : BaseFragment<FragmentSetGoalBinding>() {

    override val viewModel by viewModels {
        SetGoalViewModel(appViewModel)
    }

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentSetGoalBinding {
        return FragmentSetGoalBinding.inflate(inflater).also {
            it.viewModel = viewModel
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setToolbarVisibility(true)

        binding.btnIncrease.setOnClickListener {
            var goalValue: Int = binding.tvStepCount.text.toString().toInt()
            goalValue += 50
            binding.tvStepCount.text = goalValue.toString()
        }
        binding.btnDecrease.setOnClickListener {
            var goalValue: Int = binding.tvStepCount.text.toString().toInt()
            goalValue -= 50
            if (goalValue < 0) {
                goalValue = 0
            }
            binding.tvStepCount.text = goalValue.toString()
        }
        binding.btnChangeGoal.setOnClickListener {
            var goalValue: Int = binding.tvStepCount.text.toString().toInt()
            val response = viewModel.setGoal(goalValue)
            // Make Toast
            Toast.makeText(requireContext(), response, Toast.LENGTH_SHORT).show()

        }

    }

}