package com.ketarketir.tiketkuioflight.model.airport


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("city")
    val city: String,
    @SerializedName("code")
    val code: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("updatedAt")
    val updatedAt: String
)