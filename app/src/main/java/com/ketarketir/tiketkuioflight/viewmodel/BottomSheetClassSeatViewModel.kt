package com.ketarketir.tiketkuioflight.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class BottomSheetClassSeatViewModel  : ViewModel() {
    private val _seatClass:MutableLiveData<String> = MutableLiveData()
    val seatClass: LiveData<String> get() = _seatClass

    fun setSeatClass(seat: String) {
        _seatClass.postValue(seat)
    }
}