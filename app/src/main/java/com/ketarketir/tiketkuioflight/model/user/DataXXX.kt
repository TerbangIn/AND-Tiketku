package com.ketarketir.tiketkuioflight.model.user


import com.google.gson.annotations.SerializedName

data class DataXXX(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("expiration_time")
    val expirationTime: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("google_id")
    val googleId: Any,
    @SerializedName("id")
    val id: Int,
    @SerializedName("last_name")
    val lastName: Any,
    @SerializedName("notification")
    val notification: List<Notification>,
    @SerializedName("otp")
    val otp: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("phone_number")
    val phoneNumber: String,
    @SerializedName("role")
    val role: String,
    @SerializedName("transaction")
    val transaction: List<Transaction>,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("verified")
    val verified: Boolean
)