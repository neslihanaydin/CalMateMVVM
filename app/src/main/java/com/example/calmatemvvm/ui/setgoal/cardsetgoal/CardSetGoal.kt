package com.example.calmatemvvm.ui.setgoal.cardsetgoal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.calmatemvvm.R
import com.example.calmatemvvm.databinding.DailyStepSetBinding
import com.example.calmatemvvm.ui.common.BaseFragment
import com.example.calmatemvvm.common.viewModels


class CardSetGoal : BaseFragment<DailyStepSetBinding>() {

    override val viewModel by viewModels {
        CardSetGoalViewModel()
    }
    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): DailyStepSetBinding {
        return DailyStepSetBinding.inflate(inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addAGoalButton.setOnClickListener{
            appViewModel.navigationUnit.navigate(
                R.id.setGoalFragment,
                null
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


}