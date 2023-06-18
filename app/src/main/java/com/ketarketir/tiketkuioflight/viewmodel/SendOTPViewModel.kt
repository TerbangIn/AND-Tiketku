package com.ketarketir.tiketkuioflight.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ketarketir.tiketkuioflight.model.user.DataPostUserVerify
import com.ketarketir.tiketkuioflight.model.user.DataResponseVerifyUser
import com.ketarketir.tiketkuioflight.networking.ApiClient
import com.ketarketir.tiketkuioflight.networking.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SendOTPViewModel : ViewModel() {
    private val _statusVerify : MutableLiveData<String> = MutableLiveData()
    val statusVerify : LiveData<String> get() = _statusVerify

    fun callApiVerifyUser(email : String, otp : String){
        ApiClient.RetrofitClient.instance.verifyUser(DataPostUserVerify(email, otp))
            .enqueue(object : Callback<DataResponseVerifyUser>{
                @SuppressLint("NullSafeMutableLiveData")
                override fun onResponse(
                    call: Call<DataResponseVerifyUser>,
                    response: Response<DataResponseVerifyUser>
                ) {
                    if (response.isSuccessful){
                        val data = response.body()
                        _statusVerify.postValue(data!!.status)
                    } else{
                        Log.e("Error : ", "onFailure : ${response.message()}")
                        _statusVerify.postValue(null)
                    }
                }

                @SuppressLint("NullSafeMutableLiveData")
                override fun onFailure(call: Call<DataResponseVerifyUser>, t: Throwable) {
                    Log.e("Error : ", "onFailure : ${t.message}")
                    _statusVerify.postValue(null)
                }

            })
    }
}