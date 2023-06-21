package com.ketarketir.tiketkuioflight.networking

import com.ketarketir.tiketkuioflight.model.airport.DataResponseAirport
import com.ketarketir.tiketkuioflight.model.user.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    //user
    @GET("api/v1/user")
    fun getAllUser(@Header("Authorization") bearerToken: String): Call<List<Data>>

    @GET("api/v1/user/{id}")
    fun getDetailUser(
        @Header("Authorization") bearerToken: String,
        @Path("id") id: Int
    ): Call<Data>

    @POST("api/v1/user/login")
    fun loginUser(
        @Body request: DataPostUserLogin
    ): Call<DataResponseUserLogin>

    @POST("api/v1/user/register")
    fun registerUser(
        @Body request: DataPostUser
    ): Call<DataResponseUserRegister>

    @POST("api/v1/user/verify")
    fun verifyUser(
        @Body request : DataPostUserVerify
    ) : Call<DataResponseVerifyUser>

    @POST("api/v1/user/otp")
    fun generateOtp(
        @Body request : DataPostGenerateOtp
    ) : Call<DataResponseGenerateOtp>

    //airport
    @GET("api/v1/airport/")
    fun getListAllAirport(@Header("Authorization") bearerToken: String) : Call<List<DataResponseAirport>>

    @GET("api/v1/airport/{id}")
    fun getDetailAirport(
        @Header("Authorization") bearerToken: String,
        @Path("id") id:Int
    ) : Call<com.ketarketir.tiketkuioflight.model.airport.Data>

    //notifications
    @GET("api/v1/notification/{id}")
    fun getNotifications(
        @Header("Authorization") bearerToken: String,
        @Path("id") id: Int?
    ): Call<List<com.ketarketir.tiketkuioflight.model.notifications.Data>>
}