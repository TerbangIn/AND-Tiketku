package com.ketarketir.tiketkuioflight.viewmodel

import com.ketarketir.tiketkuioflight.model.user.DataResetPassword
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ketarketir.tiketkuioflight.model.user.DataResponseResetPassword
import com.ketarketir.tiketkuioflight.networking.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class NewPasswordViewModel @Inject constructor(private val apiService: ApiService) : ViewModel() {

    private val _statusResetPassword: MutableLiveData<DataResponseResetPassword?> = MutableLiveData()
    val statusResetPassword: MutableLiveData<DataResponseResetPassword?> get() = _statusResetPassword

    fun resetPassword(email: String, otp: String,password: String,confirm_password:String) {
        apiService.resetPassword(
            DataResetPassword(
                email = email,
                otp = otp,
                password = password,
                confirm_password = confirm_password
            )
        ).enqueue(object : Callback<DataResponseResetPassword> {
            override fun onResponse(
                call: Call<DataResponseResetPassword>,
                response: Response<DataResponseResetPassword>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    _statusResetPassword.postValue(data!!)
                } else {
                    Log.e("Error:", "onFailure: ${response.message()}")
                    _statusResetPassword.postValue(null)
                }
            }

            override fun onFailure(call: Call<DataResponseResetPassword>, t: Throwable) {
                Log.e("Error:", "onFailure: ${t.message}")
                _statusResetPassword.postValue(null)
            }
        })
    }
}
