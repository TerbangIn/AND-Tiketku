package com.ketarketir.tiketkuioflight.model.user


import com.google.gson.annotations.SerializedName

data class DataXX(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("google_id")
    val googleId: Any,
    @SerializedName("id")
    val id: Int,
    @SerializedName("last_name")
    val lastName: Any,
    @SerializedName("otp")
    val otp: Any,
    @SerializedName("password")
    val password: String,
    @SerializedName("phone_number")
    val phoneNumber: String,
    @SerializedName("role")
    val role: String,
    @SerializedName("updatedAt")
    val updatedAt: String
)