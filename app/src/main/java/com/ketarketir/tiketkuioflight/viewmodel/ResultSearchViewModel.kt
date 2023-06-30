package com.ketarketir.tiketkuioflight.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ketarketir.tiketkuioflight.datastoreprefs.UserManager
import com.ketarketir.tiketkuioflight.model.flight.Data
import com.ketarketir.tiketkuioflight.model.flight.DataResponseFlight
import com.ketarketir.tiketkuioflight.networking.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ResultSearchViewModel @Inject constructor (val apiService: ApiService, val userManager: UserManager) : ViewModel() {
    private val _token: MutableLiveData<String> = MutableLiveData()
    val token : LiveData<String> get() = _token

    private val _listFlight: MutableLiveData<List<Data>> = MutableLiveData()
    val listFligh: LiveData<List<Data>> get() = _listFlight


    fun callApiGetListFlight(bearerToken:String){
        apiService.getListAllFlight(bearerToken).enqueue(object : Callback<DataResponseFlight> {
            @SuppressLint("NullSafeMutableLiveData")
            override fun onResponse(
                call: Call<DataResponseFlight>,
                response: Response<DataResponseFlight>
            ) {
                if (response.isSuccessful){
                    val data = response.body()
                    _listFlight.postValue(data!!.data)
                } else{
                    Log.e("Error : ", "onFailure : ${response.message()}")
                    _listFlight.postValue(null)
                }
            }

            override fun onFailure(call: Call<DataResponseFlight>, t: Throwable) {
                Log.e("Error : ", "onFailure : ${t.message}")
            }
        })
    }

    fun getToken(){
        GlobalScope.launch {
            val token = userManager.getToken()
            _token.postValue(token)
        }
    }
}