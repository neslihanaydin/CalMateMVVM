package com.example.calmatemvvm.ui.meditationlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.calmatemvvm.R
import com.example.calmatemvvm.model.Meditation
import com.example.calmatemvvm.model.MeditationHolder
import com.example.calmatemvvm.ui.common.BaseViewModel

class MeditationListViewModel() : BaseViewModel() {
    private val _meditationList = MutableLiveData<List<MeditationHolder>>()
    val meditationList: LiveData<List<MeditationHolder>> = _meditationList
    private val _selectedIndex = MutableLiveData<Int>()
    val selectedIndex: LiveData<Int> = _selectedIndex

    fun createMeditationList() {
        val list = mutableListOf<Meditation>()
        list.add(Meditation(
            R.drawable.meditation1, "Meditation 1", 100,"Meditation description",
            R.raw.minute_meditation,"Daily"))
        list.add(Meditation(
            R.drawable.meditation2, "Meditation 2", 100,"Meditation description",
            R.raw.minute_meditation,"Daily"))
        list.add(Meditation(
            R.drawable.meditation3, "Meditation 3", 300,"Meditation description",
            R.raw.minute_meditation,"Daily"))
        list.add(Meditation(
            R.drawable.meditation4, "Meditation 4", 100,"Meditation description",
            R.raw.minute_meditation,"Daily"))
        list.add(Meditation(
            R.drawable.meditation1, "Meditation 1", 100,"Meditation description",
            R.raw.minute_meditation,"Daily"))
        list.add(Meditation(
            R.drawable.meditation2, "Meditation 2", 100,"Meditation description",
            R.raw.minute_meditation,"Daily"))
        list.add(Meditation(
            R.drawable.meditation3, "Meditation 3", 300,"Meditation description",
            R.raw.minute_meditation,"Daily"))
        list.add(Meditation(
            R.drawable.meditation4, "Meditation 4", 100,"Meditation description",
            R.raw.minute_meditation,"Daily"))
        list.add(Meditation(
            R.drawable.meditation1, "Meditation 1", 100,"Meditation description",
            R.raw.minute_meditation,"Daily"))
        list.add(Meditation(
            R.drawable.meditation2, "Meditation 2", 100,"Meditation description",
            R.raw.minute_meditation,"Daily"))
        list.add(Meditation(
            R.drawable.meditation3, "Meditation 3", 300,"Meditation description",
            R.raw.minute_meditation,"Daily"))
        list.add(Meditation(
            R.drawable.meditation4, "Meditation 4", 100,"Meditation description",
            R.raw.minute_meditation,"Daily"))

        val viewHolderList = list.mapIndexed { index, meditation ->
            MeditationHolder (
                meditation,
                onClick = {
                    _selectedIndex.value = index
                }
            )
        }
        _meditationList.value = viewHolderList
    }

    fun getSelectedItem(index: Int): MeditationHolder? {
        return meditationList.value?.getOrNull(index)
    }
}