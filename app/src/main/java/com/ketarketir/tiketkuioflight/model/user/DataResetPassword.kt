package com.ketarketir.tiketkuioflight.model.user

import com.google.gson.annotations.SerializedName

data class DataResetPassword(
    @SerializedName("email") val email: String,
    @SerializedName("otp") val otp: String,
    @SerializedName("password") val password: String,
    @SerializedName("confirm_password") val confirm_password: String
)
