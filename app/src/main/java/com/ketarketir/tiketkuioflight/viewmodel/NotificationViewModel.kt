package com.ketarketir.tiketkuioflight.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ketarketir.tiketkuioflight.model.notifications.Data
import com.ketarketir.tiketkuioflight.networking.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotificationViewModel : ViewModel() {
    private val _notifications = MutableLiveData<List<Data>>()
    val notifications: LiveData<List<Data>> get() = _notifications

    fun getNotifications(bearerToken: String, id: Int?) {
        val apiService = ApiClient.RetrofitClient.instance
        val call = apiService.getNotifications(bearerToken, id)
        call.enqueue(object : Callback<List<Data>> {
            override fun onResponse(call: Call<List<Data>>, response: Response<List<Data>>) {
                if (response.isSuccessful) {
                    val notifications = response.body() ?: emptyList()
                    _notifications.postValue(notifications)
                } else {
                    Log.e("API Error", "Response unsuccessful: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Data>>, t: Throwable) {
                Log.e("API Error", "Failure: ${t.message}")
            }
        })
    }
}

