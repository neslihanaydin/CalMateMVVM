package com.example.calmatemvvm.ui.fitness

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import com.example.calmatemvvm.R
import com.example.calmatemvvm.common.viewModels
import com.example.calmatemvvm.databinding.FragmentFitnessBinding
import com.example.calmatemvvm.ui.common.BaseFragment

class FitnessFragment : BaseFragment<FragmentFitnessBinding>() {

    private lateinit var textViewSteps: TextView
    private lateinit var btnDailyGoal: Button
    private lateinit var tvSuccess: TextView
    private lateinit var imgFinishLine: ImageView
    private var dailyStep: Int = 0

    override val viewModel by viewModels {
        FitnessViewModel(appViewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_fitness, container, false)
        requireContext()
        textViewSteps = rootView.findViewById(R.id.steps)
        tvSuccess = rootView.findViewById(R.id.tvSuccess)
        imgFinishLine = rootView.findViewById(R.id.imgFinishLine)
        viewModel.getDailyFitnessData(rootView.context).observe(viewLifecycleOwner, Observer {  DailyFitness->
            dailyStep = DailyFitness.stepCount
            textViewSteps.text = dailyStep.toString() + " "
            checkGoal(dailyStep)
        })

        return rootView
    }

    private fun checkGoal(dailyStep: Int) {

        if(viewModel.checkGoalReached(dailyStep)){
            tvSuccess.visibility = View.VISIBLE
            imgFinishLine.visibility = View.VISIBLE
        }
    }

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentFitnessBinding {
        binding.tvSuccess.visibility = View.INVISIBLE
        binding.imgFinishLine.visibility = View.INVISIBLE
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