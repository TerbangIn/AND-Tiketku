package com.ketarketir.tiketkuioflight.networking

import com.ketarketir.tiketkuioflight.model.user.Data
import com.ketarketir.tiketkuioflight.model.user.DataPostUser
import com.ketarketir.tiketkuioflight.model.user.DataPostUserLogin
import com.ketarketir.tiketkuioflight.model.user.DataX
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    //user

    @GET("user")
    fun getAllUser(@Header("Authorization") bearerToken: String): Call<List<Data>>

    @GET("user")
    fun getDetailUser(@Header("Authorization") bearerToken: String): Call<Data>

    @POST("user/login")
    fun loginUser(
        @Body request: DataPostUserLogin
    ): Call<DataX>

    @POST("user/register")
    fun registerUser(
        @Body request: DataPostUser
    ): Call<List<Data>>
}