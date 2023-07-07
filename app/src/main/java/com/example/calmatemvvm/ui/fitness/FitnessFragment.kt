package com.example.calmatemvvm.ui.fitness

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.calmatemvvm.common.viewModels
import com.example.calmatemvvm.databinding.FragmentFitnessBinding
import com.example.calmatemvvm.ui.common.BaseFragment

class FitnessFragment : BaseFragment<FragmentFitnessBinding>() {

    override val viewModel by viewModels {
        FitnessViewModel()
    }

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentFitnessBinding {
        return FragmentFitnessBinding.inflate(inflater).also {
            it.viewModel = viewModel
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}