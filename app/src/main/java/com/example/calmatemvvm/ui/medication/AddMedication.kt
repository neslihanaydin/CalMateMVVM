package com.example.calmatemvvm.ui.medication

import android.content.ContentValues
import android.content.Context
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
import java.util.Calendar
import java.util.Date
import java.util.TimeZone


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

            // Event start time  with date

            // Calculate the today
            val currentDate = Date()
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            val today = sdf.format(currentDate)

            // Find the closest day starting from today
            val calendar = Calendar.getInstance()
            calendar.time = sdf.parse(today)!!

            var startMonth = 0
            var startDay = 0
            var startHour = selectedHour
            var startMinute = selectedMinute
            var startYear = 0
            // Check the next 7 days starting from today
            val maxDaysToCheck = 7
            for (i in 0 until maxDaysToCheck) {
                val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
                val dayCode = when (dayOfWeek) {
                    Calendar.MONDAY -> "MO"
                    Calendar.TUESDAY -> "TU"
                    Calendar.WEDNESDAY -> "WE"
                    Calendar.THURSDAY -> "TH"
                    Calendar.FRIDAY -> "FR"
                    Calendar.SATURDAY -> "SA"
                    Calendar.SUNDAY -> "SU"
                    else -> ""
                }
                if (dayCode in selectedDays) {
                    // Find the closest selected day
                    startMonth = calendar.get(Calendar.MONTH)
                    startDay = calendar.get(Calendar.DAY_OF_MONTH)
                    startYear = calendar.get(Calendar.YEAR)
                    break
                }
                // Check the next day
                calendar.add(Calendar.DAY_OF_MONTH, 1)
            }
            val calendarStart = Calendar.getInstance()
            calendarStart.set(Calendar.YEAR,startYear )
            calendarStart.set(Calendar.MONTH, startMonth)
            calendarStart.set(Calendar.DAY_OF_MONTH, startDay )
            calendarStart.set(Calendar.HOUR_OF_DAY, startHour )
            calendarStart.set(Calendar.MINUTE, startMinute)
            val startTimeInMillis = calendarStart.timeInMillis

            setCalendarEvent(
                context!!,
                binding.textInputLayoutMedicineName.editText?.text.toString(),
                startTimeInMillis,
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
        context: Context,
        title: String,
        startMillis: Long,
        selectedDays: ArrayList<String>
    ) {
        val values = ContentValues().apply {
            put(CalendarContract.Events.TITLE, title)
            put(CalendarContract.Events.DTSTART, startMillis)
            put(CalendarContract.Events.DURATION, "P1H")
            put(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().id)
            put(CalendarContract.Events.CALENDAR_ID, 1)
            put(CalendarContract.Events.RRULE, "FREQ=WEEKLY;BYDAY=${selectedDays.joinToString(",")};WKST=SU")
            put(CalendarContract.Events.DESCRIPTION, "Don't forget to take your medicine")
            put(CalendarContract.Events.ALL_DAY, false)
        }

        val eventUri = context.contentResolver.insert(CalendarContract.Events.CONTENT_URI, values)

        if (eventUri != null) {
            val viewEventIntent = Intent(Intent.ACTION_VIEW)
            viewEventIntent.data = eventUri
            cleanForm()
            startActivity(viewEventIntent)
        }
    }
    private fun cleanForm(){
        // clean the form
        binding.textInputLayoutMedicineName.editText?.setText("")
        binding.takeMonday.isChecked = false
        binding.takeTuesday.isChecked = false
        binding.takeWednesday.isChecked = false
        binding.takeThursday.isChecked = false
        binding.takeFriday.isChecked = false
        binding.takeSaturday.isChecked = false
        binding.takeSunday.isChecked = false
        binding.linearLayoutShowTime?.visibility = View.GONE
        binding.linearLayoutAddTime?.visibility = View.VISIBLE
    }
}