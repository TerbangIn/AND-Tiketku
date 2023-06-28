package com.ketarketir.tiketkuioflight.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BottomSheetSetPassengerViewModel : ViewModel() {
    private val _countPassenger = MutableLiveData<String>()
    val countPassenger : LiveData<String> = _countPassenger

    fun setCountPassenger(count: String){
        _countPassenger.postValue(count)
    }
}
