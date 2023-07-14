package com.example.calmatemvvm.ui.fitness

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import com.example.calmatemvvm.R
import com.example.calmatemvvm.common.viewModels
import com.example.calmatemvvm.databinding.FragmentFitnessBinding
import com.example.calmatemvvm.ui.common.BaseFragment

class FitnessFragment : BaseFragment<FragmentFitnessBinding>() {

    private lateinit var textViewSteps: TextView
    private lateinit var btnDailyGoal: Button

    override val viewModel by viewModels {
        FitnessViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_fitness, container, false)
        requireContext()
        textViewSteps = rootView.findViewById(R.id.steps)

        viewModel.getDailyFitnessData(rootView.context).observe(viewLifecycleOwner, Observer {  DailyFitness->
            textViewSteps.text = DailyFitness.stepCount.toString()
        })

        return rootView
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
        setToolbarVisibility(true)

        btnDailyGoal = view.findViewById(R.id.btnSetDailyGoal)
        btnDailyGoal.setOnClickListener{
            appViewModel.navigationUnit.navigate(
                R.id.setGoalFragment,
                null
            )
        }
    }
}