package com.ketarketir.tiketkuioflight.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ketarketir.tiketkuioflight.model.notifications.Data
import com.ketarketir.tiketkuioflight.model.notifications.DataResponseNotifications
import com.ketarketir.tiketkuioflight.model.user.DataResponseUserNotification
import com.ketarketir.tiketkuioflight.model.user.DataXXX
import com.ketarketir.tiketkuioflight.model.user.Notification
import com.ketarketir.tiketkuioflight.networking.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(private val apiService: ApiService) : ViewModel() {
    private val _notifications = MutableLiveData<List<Notification>>()
    val notifications: LiveData<List<Notification>> get() = _notifications

    fun getNotifications(bearerToken: String) {
        val call = apiService.getNotifications(bearerToken)
        call.enqueue(object : Callback<DataXXX> {
            @SuppressLint("NullSafeMutableLiveData")
            override fun onResponse(
                call: Call<DataXXX>,
                response: Response<DataXXX>
            ) {
                if (response.isSuccessful) {
                    val dataResponse = response.body()
                    _notifications.postValue(dataResponse!!.notification)
                } else {
                    Log.e("API Error", "Response unsuccessful: ${response.code()}")
                    _notifications.postValue(null)
                }
            }

            @SuppressLint("NullSafeMutableLiveData")
            override fun onFailure(call: Call<DataXXX>, t: Throwable) {
                Log.e("API Error", "Failure: ${t.message}")
                _notifications.postValue(null)
            }
        })
    }
}
