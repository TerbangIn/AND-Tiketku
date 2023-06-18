package com.ketarketir.tiketkuioflight.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ketarketir.tiketkuioflight.model.airport.DataResponseAirport
import com.ketarketir.tiketkuioflight.networking.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BottomSheetHomeSearchDestinationViewModel : ViewModel() {
    private val _airport : MutableLiveData<List<DataResponseAirport>> = MutableLiveData()
    val airport : LiveData<List<DataResponseAirport>> get() = _airport


    fun callApiAirport(bearerToken: String){
        ApiClient.RetrofitClient.instance.getListAllAirport(bearerToken)
            .enqueue(object : Callback<List<DataResponseAirport>>{
                @SuppressLint("NullSafeMutableLiveData")
                override fun onResponse(
                    call: Call<List<DataResponseAirport>>,
                    response: Response<List<DataResponseAirport>>
                ) {
                    if (response.isSuccessful){
                        val data = response.body()
                        _airport.postValue(data)
                    } else{
                        Log.e("Error : ", "onFailure : ${response.message()}")
                        _airport.postValue(null)
                    }
                }

                override fun onFailure(call: Call<List<DataResponseAirport>>, t: Throwable) {
                    Log.e("Error : ", "onFailure : ${t.message}")
                }
            })
    }
}