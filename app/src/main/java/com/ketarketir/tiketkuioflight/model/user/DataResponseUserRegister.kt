package com.ketarketir.tiketkuioflight.model.user


import com.google.gson.annotations.SerializedName

data class DataResponseUserRegister(
    @SerializedName("data")
    val `data`: DataXX,
    @SerializedName("status")
    val status: String
)