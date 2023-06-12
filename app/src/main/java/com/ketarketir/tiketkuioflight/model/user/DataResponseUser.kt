package com.ketarketir.tiketkuioflight.model.user


import com.google.gson.annotations.SerializedName
import com.ketarketir.tiketkuioflight.model.user.Data

data class DataResponseUser(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("status")
    val status: String
)