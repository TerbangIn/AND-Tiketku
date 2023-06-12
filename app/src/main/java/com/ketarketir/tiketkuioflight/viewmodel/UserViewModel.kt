package com.ketarketir.tiketkuioflight.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ketarketir.tiketkuioflight.datastoreprefs.UserManager
import com.ketarketir.tiketkuioflight.model.user.Data
import com.ketarketir.tiketkuioflight.model.user.DataPostUser
import com.ketarketir.tiketkuioflight.model.user.DataPostUserLogin
import com.ketarketir.tiketkuioflight.model.user.DataX
import com.ketarketir.tiketkuioflight.networking.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel:ViewModel() {

    private val _users: MutableLiveData<List<Data>> = MutableLiveData()
    val users : LiveData<List<Data>> get() = _users

    private val _detailUser : MutableLiveData<Data> = MutableLiveData()
    val detailUser : LiveData<Data> get() = _detailUser

    private val _token:MutableLiveData<String> = MutableLiveData()
    val token : LiveData<String> get() = _token



    fun callApiPostRegisterUser(email :String, password:String, first_name:String, phone_number:String){
        ApiClient.RetrofitClient.instance.registerUser(DataPostUser(email,password, first_name, phone_number))
            .enqueue(object :Callback<List<Data>>{
                @SuppressLint("NullSafeMutableLiveData")
                override fun onResponse(call: Call<List<Data>>, response: Response<List<Data>>) {
                    if (response.isSuccessful){
                        val data = response.body()
                        _users.postValue(data!!)
                    } else{
                        _users.postValue(null)
                    }
                }

                @SuppressLint("NullSafeMutableLiveData")
                override fun onFailure(call: Call<List<Data>>, t: Throwable) {
                    _users.postValue(null)
                }

            })
    }

    fun callApiPostUserLogin(email: String, password: String){
        ApiClient.RetrofitClient.instance.loginUser(DataPostUserLogin(email,password))
            .enqueue(object : Callback<DataX>{
                @SuppressLint("NullSafeMutableLiveData")
                override fun onResponse(call: Call<DataX>, response: Response<DataX>) {
                    if (response.isSuccessful){
                        val data = response.body()
                        _token.postValue(data!!.token)
                    } else{
                        _token.postValue(null)
                    }
                }

                @SuppressLint("NullSafeMutableLiveData")
                override fun onFailure(call: Call<DataX>, t: Throwable) {
                    _token.postValue(null)
                }

            })
    }

    fun callApiGetAllUser(bearerToken:String){
        ApiClient.RetrofitClient.instance.getAllUser(bearerToken)
            .enqueue(object : Callback<List<Data>>{
                @SuppressLint("NullSafeMutableLiveData")
                override fun onResponse(call: Call<List<Data>>, response: Response<List<Data>>) {
                    if (response.isSuccessful){
                        val data = response.body()
                        _users.postValue(data!!)
                    }else{
                        _users.postValue(null)
                    }
                }

                @SuppressLint("NullSafeMutableLiveData")
                override fun onFailure(call: Call<List<Data>>, t: Throwable) {
                    _users.postValue(null)
                }

            })
    }

    fun callApiGetDetailUser(bearerToken: String){
        ApiClient.RetrofitClient.instance.getDetailUser(bearerToken)
            .enqueue(object :Callback<Data>{
                @SuppressLint("NullSafeMutableLiveData")
                override fun onResponse(call: Call<Data>, response: Response<Data>) {
                    if (response.isSuccessful){
                        val data = response.body()
                        _detailUser.postValue(data!!)
                    }else{
                        _detailUser.postValue(null)
                    }
                }

                @SuppressLint("NullSafeMutableLiveData")
                override fun onFailure(call: Call<Data>, t: Throwable) {
                    _users.postValue(null)
                }

            })
    }

}