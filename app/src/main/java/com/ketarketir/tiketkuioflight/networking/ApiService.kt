package com.ketarketir.tiketkuioflight.networking

import com.ketarketir.tiketkuioflight.model.airport.DataResponseAirport
import com.ketarketir.tiketkuioflight.model.notifications.DataResponseNotifications
import com.ketarketir.tiketkuioflight.model.user.*
import retrofit2.Call
import retrofit2.http.*

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

    // Update User
    @PUT("api/v1/user/{id}")
    fun updateUser(
        @Header("Authorization") bearerToken: String,
        @Path("id") id: Int,
        @Body request: DataUserUpdate
    ): Call<DataResponseUserUpdate>

    //airport
    @GET("api/v1/airport/")
    fun getListAllAirport(@Header("Authorization") bearerToken: String) : Call<List<DataResponseAirport>>

    @GET("api/v1/airport/{id}")
    fun getDetailAirport(
        @Header("Authorization") bearerToken: String,
        @Path("id") id:Int
    ) : Call<com.ketarketir.tiketkuioflight.model.airport.Data>

    //notifications
    @GET("api/v1/notification")
    fun getNotifications(
        @Header("Authorization") bearerToken: String
    ): Call<DataResponseNotifications>
}