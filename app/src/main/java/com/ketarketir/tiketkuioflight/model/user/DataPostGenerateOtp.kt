package com.ketarketir.tiketkuioflight.model.user

import com.google.gson.annotations.SerializedName

data class DataPostGenerateOtp(
    @SerializedName("email")
    val email: String
)
