package com.ketarketir.tiketkuioflight.model.passenger


import com.google.gson.annotations.SerializedName

data class DataResponsePassenger(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("status")
    val status: String
)