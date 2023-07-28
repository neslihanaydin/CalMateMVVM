package com.example.calmatemvvm.ui.medication.cardaddmedication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.calmatemvvm.R
import com.example.calmatemvvm.databinding.DailyStepSetBinding
import com.example.calmatemvvm.ui.common.BaseFragment
import com.example.calmatemvvm.common.viewModels
import com.example.calmatemvvm.databinding.MedicationReminderSetBinding


class CardAddMedication : BaseFragment<MedicationReminderSetBinding>() {

    override val viewModel by viewModels {
        CardAddMedicationViewModel()
    }
    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): MedicationReminderSetBinding {
        return MedicationReminderSetBinding.inflate(inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addReminderButton.setOnClickListener{
            appViewModel.navigationUnit.navigate(
                R.id.addMedication,
                null
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


}