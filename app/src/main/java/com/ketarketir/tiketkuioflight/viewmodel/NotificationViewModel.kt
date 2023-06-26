package com.ketarketir.tiketkuioflight.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ketarketir.tiketkuioflight.model.notifications.Data
import com.ketarketir.tiketkuioflight.model.notifications.DataResponseNotifications
import com.ketarketir.tiketkuioflight.networking.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(private val apiService: ApiService) : ViewModel() {
    private val _notifications = MutableLiveData<List<Data>>()
    val notifications: LiveData<List<Data>> get() = _notifications

    fun getNotifications(bearerToken: String) {
        val call = apiService.getNotifications(bearerToken)
        call.enqueue(object : Callback<DataResponseNotifications> {
            override fun onResponse(
                call: Call<DataResponseNotifications>,
                response: Response<DataResponseNotifications>
            ) {
                if (response.isSuccessful) {
                    val dataResponse = response.body()
                    val notifications = dataResponse?.data ?: emptyList()
                    _notifications.postValue(notifications)
                } else {
                    Log.e("API Error", "Response unsuccessful: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<DataResponseNotifications>, t: Throwable) {
                Log.e("API Error", "Failure: ${t.message}")
            }
        })
    }
}
