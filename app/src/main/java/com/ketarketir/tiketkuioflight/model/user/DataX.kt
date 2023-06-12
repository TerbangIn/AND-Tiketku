package com.ketarketir.tiketkuioflight.model.user


import com.google.gson.annotations.SerializedName

data class DataX(
    @SerializedName("token")
    val token: String,
    @SerializedName("users")
    val users: Users
)