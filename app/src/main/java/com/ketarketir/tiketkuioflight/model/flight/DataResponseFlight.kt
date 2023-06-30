package com.ketarketir.tiketkuioflight.model.flight


import com.google.gson.annotations.SerializedName

data class DataResponseFlight(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("status")
    val status: String
)