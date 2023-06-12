package com.ketarketir.tiketkuioflight.model.user


import com.google.gson.annotations.SerializedName

data class DataResponseUserLogin(
    @SerializedName("data")
    val `data`: DataX,
    @SerializedName("status")
    val status: String
)