package com.ketarketir.tiketkuioflight.model.user

import com.google.gson.annotations.SerializedName

data class DataResponseUserUpdate(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)