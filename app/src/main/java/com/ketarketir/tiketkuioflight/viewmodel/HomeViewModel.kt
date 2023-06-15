package com.ketarketir.tiketkuioflight.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class HomeViewModel : ViewModel() {

    private val _selectedStartDate: MutableLiveData<Date> = MutableLiveData()
    val selectedStartDate : LiveData<Date> get() = _selectedStartDate

    private val _selectedEndDate: MutableLiveData<Date> = MutableLiveData()
    val selectedEndDate : LiveData<Date> get() = _selectedEndDate

    fun postSelectedStartDate(date: Date) {
        _selectedStartDate.postValue(date)
    }

    fun postSelectedEndDate(date: Date) {
        _selectedEndDate.postValue(date)
    }

//    var selectedStartDate: Date? = null
//    var selectedEndDate: Date? = null
}