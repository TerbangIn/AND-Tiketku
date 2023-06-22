package com.ketarketir.tiketkuioflight.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ketarketir.tiketkuioflight.datastoreprefs.UserManager
import com.ketarketir.tiketkuioflight.model.user.DataPostGenerateOtp
import com.ketarketir.tiketkuioflight.model.user.DataPostUserVerify
import com.ketarketir.tiketkuioflight.model.user.DataResponseGenerateOtp
import com.ketarketir.tiketkuioflight.model.user.DataResponseVerifyUser
import com.ketarketir.tiketkuioflight.networking.ApiClient
import com.ketarketir.tiketkuioflight.networking.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SendOTPViewModel @Inject constructor(val apiService: ApiService) : ViewModel() {
    private val _statusVerify : MutableLiveData<DataResponseVerifyUser> = MutableLiveData()
    val statusVerify : LiveData<DataResponseVerifyUser> get() = _statusVerify

    private val _statusGenerate : MutableLiveData<DataResponseGenerateOtp> = MutableLiveData()
    val statusGenerate : LiveData<DataResponseGenerateOtp> get() = _statusGenerate

    fun callApiVerifyUser(email : String, otp : String){
        apiService.verifyUser(DataPostUserVerify(email, otp))
            .enqueue(object : Callback<DataResponseVerifyUser>{
                @SuppressLint("NullSafeMutableLiveData")
                override fun onResponse(
                    call: Call<DataResponseVerifyUser>,
                    response: Response<DataResponseVerifyUser>
                ) {
                    if (response.isSuccessful){
                        val data = response.body()
                        _statusVerify.postValue(data!!)
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

    fun callApiPostGenerateOtp(email: String){
        apiService.generateOtp(DataPostGenerateOtp(email)).enqueue(object : Callback<DataResponseGenerateOtp>{
            @SuppressLint("NullSafeMutableLiveData")
            override fun onResponse(
                call: Call<DataResponseGenerateOtp>,
                response: Response<DataResponseGenerateOtp>
            ) {
                if (response.isSuccessful){
                    val data = response.body()
                    _statusGenerate.postValue(data!!)
                } else{
                    _statusGenerate.postValue(null)
                    Log.e("Error : ", "onFailure : ${response.message()}")
                }
            }
            override fun onFailure(call: Call<DataResponseGenerateOtp>, t: Throwable) {
                Log.e("Error : ", "onFailure : ${t.message}")
            }
        })
    }
}