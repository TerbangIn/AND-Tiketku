package com.ketarketir.tiketkuioflight.networking

import com.ketarketir.tiketkuioflight.model.user.Data
import com.ketarketir.tiketkuioflight.model.user.DataPostUser
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    //user

    @GET("users")
    fun getAllUser(@Header("Authorization") bearerToken: String): Call<List<Data>>

    @GET("users")
    fun getDetailUser(@Header("Authorization") bearerToken: String): Call<Data>

    @POST("users")
    fun registerUser(
        @Body request: DataPostUser
    ): Call<List<Data>>
}