package com.ketarketir.tiketkuioflight.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ketarketir.tiketkuioflight.datastoreprefs.UserManager
import com.ketarketir.tiketkuioflight.model.user.*
import com.ketarketir.tiketkuioflight.networking.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import com.ketarketir.tiketkuioflight.model.user.DataResponseResetPassword
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(val apiService: ApiService, val userManager: UserManager):ViewModel() {

    private val _loginUsers: MutableLiveData<Users> = MutableLiveData()
    val loginUsers : LiveData<Users> get() = _loginUsers

    private val _registerUser : MutableLiveData<DataXX> = MutableLiveData()
    val registerUser : LiveData<DataXX> get() = _registerUser

    private val _users: MutableLiveData<List<Data>> = MutableLiveData()
    val users : LiveData<List<Data>> get() = _users

    private val _detailUser : MutableLiveData<Data> = MutableLiveData()
    val detailUser : LiveData<Data> get() = _detailUser

    private val _token:MutableLiveData<String> = MutableLiveData()
    val token : LiveData<String> get() = _token

    private var loggedInUserId: Int? = null


    fun callApiPostRegisterUser(email :String, password:String, first_name:String, phone_number:String){
        apiService.registerUser(DataPostUser(email,password, first_name, phone_number))
            .enqueue(object :Callback<DataResponseUserRegister>{
                @SuppressLint("NullSafeMutableLiveData")
                override fun onResponse(call: Call<DataResponseUserRegister>, response: Response<DataResponseUserRegister>) {
                    if (response.isSuccessful){
                        val data = response.body()
                        _registerUser.postValue(data!!.data)
                    } else{
                        Log.e("Error : ", "onFailure : ${response.message()}")
                        _registerUser.postValue(null)
                    }
                }

                @SuppressLint("NullSafeMutableLiveData")
                override fun onFailure(call: Call<DataResponseUserRegister>, t: Throwable) {
                    Log.e("Error : ", "onFailure : ${t.message}")
                    _registerUser.postValue(null)
                }

            })
    }

    fun callApiPostUserLogin(email: String, password: String) {
        apiService.loginUser(DataPostUserLogin(email, password))
            .enqueue(object : Callback<DataResponseUserLogin> {
                @SuppressLint("NullSafeMutableLiveData")
                override fun onResponse(
                    call: Call<DataResponseUserLogin>,
                    response: Response<DataResponseUserLogin>
                ) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        _token.postValue(data!!.data.token)
                        _loginUsers.postValue(data!!.data.users)
                    } else {
                        _token.postValue(null)
                        _loginUsers.postValue(null)
                        Log.e("Error:", "onFailure: ${response.message()}")
                    }
                }

                @SuppressLint("NullSafeMutableLiveData")
                override fun onFailure(call: Call<DataResponseUserLogin>, t: Throwable) {
                    Log.e("Error:", "onFailure: ${t.message}")
                    _loginUsers.postValue(null)
                    _token.postValue(null)
                }
            })
    }

    fun callApiGetAllUser(bearerToken:String){
        apiService.getAllUser(bearerToken)
            .enqueue(object : Callback<List<Data>>{
                @SuppressLint("NullSafeMutableLiveData")
                override fun onResponse(call: Call<List<Data>>, response: Response<List<Data>>) {
                    if (response.isSuccessful){
                        val data = response.body()
                        _users.postValue(data!!)
                    }else{
                        Log.e("Error : ", "onFailure : ${response.message()}")
                    }
                }

                @SuppressLint("NullSafeMutableLiveData")
                override fun onFailure(call: Call<List<Data>>, t: Throwable) {
                    Log.e("Error : ", "onFailure : ${t.message}")
                }

            })
    }

    fun callApiGetDetailUser(bearerToken: String, id:Int){
        apiService.getDetailUser(bearerToken, id)
            .enqueue(object :Callback<Data>{
                @SuppressLint("NullSafeMutableLiveData")
                override fun onResponse(call: Call<Data>, response: Response<Data>) {
                    if (response.isSuccessful){
                        val data = response.body()
                        _detailUser.postValue(data!!)
                    }else{
                        Log.e("Error : ", "onFailure : ${response.message()}")
                    }
                }

                @SuppressLint("NullSafeMutableLiveData")
                override fun onFailure(call: Call<Data>, t: Throwable) {
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

    fun getUserId(): Int? {
        return loggedInUserId
    }

    fun resetPassword(email: String, otp: String, password: String, confirm_password: String): LiveData<DataResponseResetPassword> {
        val resetPasswordResponse: MutableLiveData<DataResponseResetPassword> = MutableLiveData()

        val request = DataResetPassword(email,otp,password, confirm_password )
        apiService.resetPassword(request).enqueue(object : Callback<DataResponseResetPassword> {
            override fun onResponse(
                call: Call<DataResponseResetPassword>,
                response: Response<DataResponseResetPassword>
            ) {
                if (response.isSuccessful) {
                    resetPasswordResponse.value = response.body()
                } else {
                    Log.e("Error:", "onFailure: ${response.message()}")
                    resetPasswordResponse.value = null
                }
            }

            override fun onFailure(call: Call<DataResponseResetPassword>, t: Throwable) {
                Log.e("Error:", "onFailure: ${t.message}")
                resetPasswordResponse.value = null
            }
        })

        return resetPasswordResponse
    }

}