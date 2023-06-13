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
    @GET("/api/v1/user")
    fun getAllUser(@Header("Authorization") bearerToken: String): Call<List<Data>>

    @GET("/api/v1/user")
    fun getDetailUser(@Header("Authorization") bearerToken: String): Call<Data>

    @POST("/api/v1/user/login")
    fun loginUser(
        @Body request: DataPostUserLogin
    ): Call<DataX>

    @POST("/api/v1/user/register")
    fun registerUser(
        @Body request: DataPostUser
    ): Call<List<Data>>

    //airport

}