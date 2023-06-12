package com.ketarketir.tiketkuioflight.model.user

import com.google.gson.annotations.SerializedName

data class DataPostUserLogin(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)
