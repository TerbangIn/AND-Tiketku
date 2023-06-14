package com.ketarketir.tiketkuioflight.model.notifications


import com.google.gson.annotations.SerializedName

data class DataResponseNotifications(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("status")
    val status: String
)