package com.ketarketir.tiketkuioflight.viewmodel

import android.annotation.SuppressLint
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

    private val _selectedAirportFrom : MutableLiveData<String?> = MutableLiveData()
    val selectedAirportFrom : LiveData<String?> get() = _selectedAirportFrom

    private val _selectedAirportTo : MutableLiveData<String?> = MutableLiveData()
    val selectedAirportTo : LiveData<String?> get() = _selectedAirportTo


    fun postSelectedStartDate(date: Date) {
        _selectedStartDate.postValue(date)
    }

    @SuppressLint("NullSafeMutableLiveData")
    fun postSelectedEndDate(date: Date?) {
        _selectedEndDate.postValue(date)
    }

    fun postSelectedAirportFrom(from:String?){
        _selectedAirportFrom.postValue(from)
    }

    fun postSelectedAirportTo(To:String?){
        _selectedAirportTo.postValue(To)
    }
}