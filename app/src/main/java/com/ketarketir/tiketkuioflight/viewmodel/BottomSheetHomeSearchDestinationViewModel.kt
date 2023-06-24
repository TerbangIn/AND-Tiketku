package com.ketarketir.tiketkuioflight.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ketarketir.tiketkuioflight.datastoreprefs.UserManager
import com.ketarketir.tiketkuioflight.model.airport.DataAirport
import com.ketarketir.tiketkuioflight.model.airport.DataResponseAirport
import com.ketarketir.tiketkuioflight.networking.ApiClient
import com.ketarketir.tiketkuioflight.networking.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class BottomSheetHomeSearchDestinationViewModel @Inject constructor(val apiService: ApiService, val userManager: UserManager) : ViewModel() {
    private val _airport : MutableLiveData<DataAirport> = MutableLiveData()
    val airport : LiveData<DataAirport> get() = _airport

    private val _token:MutableLiveData<String> = MutableLiveData()
    val token : LiveData<String> get() = _token

    fun callApiAirport(bearerToken: String){
        apiService.getListAllAirport(bearerToken)
            .enqueue(object : Callback<DataAirport>{
                @SuppressLint("NullSafeMutableLiveData")
                override fun onResponse(
                    call: Call<DataAirport>,
                    response: Response<DataAirport>
                ) {
                    if (response.isSuccessful){
                        val data = response.body()
                        _airport.postValue(data)
                    } else{
                        Log.e("Error : ", "onFailure : ${response.message()}")
                        _airport.postValue(null)
                    }
                }

                override fun onFailure(call: Call<DataAirport>, t: Throwable) {
                    Log.e("Error : ", "onFailure : ${t.message}")
                }
            })
    }

    fun getToken(){
        viewModelScope.launch {
            val token = userManager.getToken()
            _token.postValue(token)
        }
    }
}