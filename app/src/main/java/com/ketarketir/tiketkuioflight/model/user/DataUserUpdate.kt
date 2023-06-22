package com.ketarketir.tiketkuioflight.model.user

import com.google.gson.annotations.SerializedName

data class DataUserUpdate(
    @SerializedName("email")
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("phone_number")
    val phoneNumber: String,
)