package com.example.calmatemvvm.ui.setgoal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.calmatemvvm.R
import com.example.calmatemvvm.common.viewModels
import com.example.calmatemvvm.databinding.FragmentSetGoalBinding
import com.example.calmatemvvm.ui.common.BaseFragment

class SetGoalFragment : BaseFragment<FragmentSetGoalBinding>() {

    override val viewModel by viewModels {
        SetGoalViewModel()
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


    }

}