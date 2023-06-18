package com.ketarketir.tiketkuioflight.model.user

import com.google.gson.annotations.SerializedName

data class DataPostUserVerify(
    @SerializedName("email")
    val email: String,
    @SerializedName("otp")
    val otp: String
)
