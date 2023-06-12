package com.ketarketir.tiketkuioflight.model.airport


import com.google.gson.annotations.SerializedName

data class DataResponseAirport(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("status")
    val status: String
)