package com.ketarketir.tiketkuioflight.model.user


import com.google.gson.annotations.SerializedName

data class Information(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("flight_id")
    val flightId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("updatedAt")
    val updatedAt: String
)