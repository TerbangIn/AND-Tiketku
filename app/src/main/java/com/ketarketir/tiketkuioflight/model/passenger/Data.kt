package com.ketarketir.tiketkuioflight.model.passenger


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("country")
    val country: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("date_of_birth")
    val dateOfBirth: String,
    @SerializedName("expired_date")
    val expiredDate: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("identity_number")
    val identityNumber: String,
    @SerializedName("identity_number_of_country")
    val identityNumberOfCountry: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("updatedAt")
    val updatedAt: String
)