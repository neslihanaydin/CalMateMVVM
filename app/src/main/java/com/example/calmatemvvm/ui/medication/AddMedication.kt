package com.example.calmatemvvm.ui.medication

import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.calmatemvvm.common.viewModels
import com.example.calmatemvvm.databinding.FragmentAddMedicationBinding
import com.example.calmatemvvm.ui.common.BaseFragment
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.text.SimpleDateFormat
import java.util.Date


class AddMedication : BaseFragment<FragmentAddMedicationBinding>() {

    private var selectedDays: ArrayList<String> = arrayListOf()
    private var selectedHour: Int = 0
    private var selectedMinute: Int = 0

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

        binding.btnAddMedication.setOnClickListener {

            if(binding.takeMonday.isChecked) {
                selectedDays.add("MO")
            }
            if (binding.takeTuesday.isChecked) {
                selectedDays.add("TU")
            }
            if (binding.takeWednesday.isChecked) {
                selectedDays.add("WE")
            }
            if (binding.takeThursday.isChecked) {
                selectedDays.add("TH")
            }
            if (binding.takeFriday.isChecked) {
                selectedDays.add("FR")
            }
            if (binding.takeSaturday.isChecked) {
                selectedDays.add("SA")
            }
            if (binding.takeSunday.isChecked) {
                selectedDays.add("SU")
            }

            // Event start and end time with date
            // get Date like 2023-03-02
            val currentDate = Date()
            val sdf = java.text.SimpleDateFormat("yyyy-MM-dd")
            val startDate = sdf.format(currentDate)
            var startTime = startDate + "T" + selectedHour + ":" + selectedMinute + ":00"
            var endTime =  startDate + "T" + (selectedHour + 1) + ":" + (selectedMinute) + ":00"

            // Parsing the date and time
            val mSimpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            val mStartTime = mSimpleDateFormat.parse(startTime)
            val mEndTime = mSimpleDateFormat.parse(endTime)

            setCalendarEvent(
                binding.textInputLayoutMedicineName.editText?.text.toString(),
                mStartTime.time,
                mEndTime.time,
                selectedDays

            )
        }

    }

    private fun showTimePicker() {
        val materialTimePicker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .build()
        materialTimePicker.addOnPositiveButtonClickListener {
            selectedHour = materialTimePicker.hour
            selectedMinute = materialTimePicker.minute
            val selectedTime = String.format("%02d:%02d", selectedHour, selectedMinute)
            binding.textViewShowTime?.setText(selectedTime)

            binding.linearLayoutShowTime?.visibility = View.VISIBLE
            binding.linearLayoutAddTime?.visibility = View.GONE

        }
        materialTimePicker.show(childFragmentManager, "time_picker")
    }

    private fun removeAlarm() {
        binding.linearLayoutShowTime?.visibility = View.GONE
        binding.linearLayoutAddTime?.visibility = View.VISIBLE

    }

    private fun setCalendarEvent(
        title: String,
        begin: Long,
        end: Long,
        selectedDays: ArrayList<String>

    ) {
        var repeatRule = ""
        for ((index, day) in selectedDays.withIndex()) {
            repeatRule += day
            if (index < selectedDays.size - 1) {
                repeatRule += ","
            }
        }

        val intent = Intent(Intent.ACTION_INSERT).apply {
            data = CalendarContract.Events.CONTENT_URI
            putExtra(CalendarContract.Events.TITLE, title)
            putExtra(CalendarContract.ExtendedProperties.DESCRIPTION, "Don't forget to take your medicine")
            putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, begin)
            putExtra(CalendarContract.EXTRA_EVENT_END_TIME, end)
            putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, false)
            putExtra(
                CalendarContract.Events.RRULE,
                "FREQ=WEEKLY;BYDAY=$repeatRule;WKST=SU"
            )
        }
        // Event reminders
        val reminderMinutes = 10 // Reminder before 10 minutes
        intent.putExtra(CalendarContract.Reminders.MINUTES, reminderMinutes)
        intent.putExtra(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT)

        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(intent)
        }
    }


}