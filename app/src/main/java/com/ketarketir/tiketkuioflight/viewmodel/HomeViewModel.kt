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

    private val _passengerCount = MutableLiveData<Int>()
    val passengerCount: LiveData<Int> get() = _passengerCount

    private val _passengerCountChild = MutableLiveData<Int>()
    val passengerCountChild: LiveData<Int> get() = _passengerCountChild

    private val _passengerCountBaby = MutableLiveData<Int>()
    val passengerCountBaby: LiveData<Int> get() = _passengerCountBaby

    private val _totalPassenger = MutableLiveData<Int>()
    val totalPassenger: LiveData<Int> get() = _totalPassenger


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

    fun incrementPassengerCount() {
        _passengerCount.value = (_passengerCount.value ?: 0) + 1
    }

    fun decrementPassengerCount() {
        val currentCount = _passengerCount.value ?: 0
        if (currentCount > 0) {
            _passengerCount.value = currentCount - 1
        }
    }
    fun incrementPassengerCountChild() {
        _passengerCountChild.value = (_passengerCountChild.value ?: 0) + 1
    }

    fun decrementPassengerCountChild() {
        val currentCount = _passengerCountChild.value ?: 0
        if (currentCount > 0) {
            _passengerCountChild.value = currentCount - 1
        }
    }
    fun incrementPassengerCountBaby() {
        _passengerCountBaby.value = (_passengerCountBaby.value ?: 0) + 1
    }

    fun decrementPassengerCountBaby() {
        val currentCount = _passengerCountBaby.value ?: 0
        if (currentCount > 0) {
            _passengerCountBaby.value = currentCount - 1
        }
    }
//    fun saveCountPassenger(count: Int){
//        _passengerCount.postValue(count)
//    }
    fun updateTotalPassenger() {
        val adultCount = passengerCount.value ?: 0
        val childCount = passengerCountChild.value ?: 0
        val babyCount = passengerCountBaby.value ?: 0
        val total = adultCount + childCount + babyCount
        _totalPassenger.value = total
    }
}