package com.example.calmatemvvm.ui.medication

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.calmatemvvm.R
import com.example.calmatemvvm.databinding.FragmentAddMedicationBinding
import com.example.calmatemvvm.ui.common.BaseFragment
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.example.calmatemvvm.common.viewModels
import java.util.Calendar


class AddMedication : BaseFragment<FragmentAddMedicationBinding>() {

    private var alarmMgr: AlarmManager? = null
    private lateinit var alarmIntent: PendingIntent

    override val viewModel by viewModels {
        AddMedicationViewModel()
    }

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentAddMedicationBinding {
        return FragmentAddMedicationBinding.inflate(inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.linearLayoutAddTime.setOnClickListener{ showTimePicker() }
        binding.removeAlarm.setOnClickListener{ removeAlarm()}

        alarmMgr = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmIntent = Intent(context, AlarmReceiver::class.java).let { intent ->
            PendingIntent.getBroadcast(context, 0, intent, 0)
        }

    }

    private fun showTimePicker() {
        val materialTimePicker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .build()
        materialTimePicker.addOnPositiveButtonClickListener {
            val hour = materialTimePicker.hour
            val minute = materialTimePicker.minute
            val selectedTime = String.format("%02d:%02d", hour, minute)
            setAlarm(selectedTime)
            binding.textViewShowTime?.setText(selectedTime)

            binding.linearLayoutShowTime?.visibility = View.VISIBLE
            binding.linearLayoutAddTime?.visibility = View.GONE

        }
        materialTimePicker.show(childFragmentManager, "time_picker")
    }

    private fun removeAlarm() {
        binding.linearLayoutShowTime?.visibility = View.GONE
        binding.linearLayoutAddTime?.visibility = View.VISIBLE
        // If the alarm has been set, cancel it.
        alarmMgr?.cancel(alarmIntent)


    }

    private fun setAlarm(time: String) {

        // Set the alarm to start at 16:21
        val calendar: Calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            val timeParts = time.split(":")
            set(Calendar.HOUR_OF_DAY, timeParts[0].toInt())
            set(Calendar.MINUTE, timeParts[1].toInt())
        }

        alarmMgr?.set(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            alarmIntent
        )


    }


}