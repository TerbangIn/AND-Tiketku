package com.ketarketir.tiketkuioflight.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ketarketir.tiketkuioflight.networking.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val apiService: ApiService) : ViewModel() {

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